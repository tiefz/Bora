package br.com.tiefenbarher.bora.presentation.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import br.com.tiefenbarher.bora.data.dao.BoraDao
import br.com.tiefenbarher.bora.databinding.FragmentHomeBinding
import br.com.tiefenbarher.bora.domain.model.AppShift
import br.com.tiefenbarher.bora.presentation.view_model.BoraViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel: BoraViewModel by viewModel()
    private val binding get() = _binding!!
    private val dao: BoraDao by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        val view = binding.root

        lifecycle.coroutineScope.launch {
            viewModel.getAllShifts().collect() { shiftList ->
                val firstShift = shiftList.first()
                if (!firstShift.isFinished) {
                    viewModel.setCurrentShift(firstShift.toAppModel())
                    val action = HomeFragmentDirections
                        .actionHomeFragmentToEntradaFragment()
                    view.findNavController().navigate(action)
                } else {
                    viewModel.deleteShift(firstShift.toAppModel())
                }
            }
        }

        binding.apply {
            ibClockStart.setOnClickListener {
                val action = HomeFragmentDirections
                    .actionHomeFragmentToEntradaFragment()

                val resetTime = "00:00"
                val specificDate = LocalDate.now()
                val localTime = LocalTime.parse(resetTime, DateTimeFormatter.ofPattern("HH:mm"))
                val formattedTime = LocalDateTime.of(specificDate, localTime)

                viewModel.saveShift(
                    AppShift(
                        start = formattedTime,
                        end = formattedTime,
                        lunch = formattedTime,
                        lunchEnd = formattedTime,
                        pauses = listOf(),
                        isFinished = false
                    )
                )
                view.findNavController().navigate(action)
            }
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}