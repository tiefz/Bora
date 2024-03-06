package br.com.tiefenbarher.bora.presentation.ui.navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import br.com.tiefenbarher.bora.R
import br.com.tiefenbarher.bora.databinding.FragmentHomeBinding
import br.com.tiefenbarher.bora.domain.model.AppShift
import br.com.tiefenbarher.bora.domain.model.repository.BoraRepository
import br.com.tiefenbarher.bora.presentation.view_model.BoraViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private const val END_TIME = "end_time"

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel: BoraViewModel by activityViewModel()
    private val binding get() = _binding!!
    private val repository: BoraRepository by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        val view = binding.root

        getPrefs()

        viewModel.shifts.observe(viewLifecycleOwner, Observer { shifts ->
            if (shifts.isNotEmpty()) {
                val firstShift = shifts.first()
                if (!firstShift.isFinished) {
                    viewModel.setCurrentShift(firstShift.toAppModel())
                    Log.i(
                        "TempoShift",
                        "Viewmodel primeira atualizaçao do valor: ${viewModel.currentShift.value}"
                    )
                    val action = HomeFragmentDirections
                        .actionHomeFragmentToEntradaFragment()
                    view.findNavController().navigate(action)
                } else {
                    viewModel.deleteShift(firstShift.toAppModel())
                }
            }
        })

        binding.apply {
            ibClockStart.setOnClickListener {
                val action = HomeFragmentDirections
                    .actionHomeFragmentToEntradaFragment()

                val resetTime = "00:00"
                val specificDate = LocalDate.now()
                val localTime = LocalTime.parse(resetTime, DateTimeFormatter.ofPattern("HH:mm"))
                val formattedTime = LocalDateTime.of(specificDate, localTime)

                lifecycleScope.launch {
                    repository.saveShift(
                        AppShift(
                            id = 1,
                            start = formattedTime,
                            end = formattedTime,
                            lunch = formattedTime,
                            lunchEnd = formattedTime,
                            pauses = listOf(),
                            isFinished = false
                        )
                    )
                }
                view.findNavController().navigate(action)
            }
        }
        // TODO: Criar dropdown da carga horaria
        return view
    }

    private fun getPrefs() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        binding.tvLastCalculatedTime.text =
            sharedPref.getString(END_TIME, getString(R.string.zero_hour))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}