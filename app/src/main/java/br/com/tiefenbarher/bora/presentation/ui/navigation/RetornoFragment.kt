package br.com.tiefenbarher.bora.presentation.ui.navigation

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import br.com.tiefenbarher.bora.databinding.FragmentRetornoBinding
import br.com.tiefenbarher.bora.domain.model.AppShift
import br.com.tiefenbarher.bora.presentation.view_model.BoraViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class RetornoFragment : Fragment() {
    private var _binding: FragmentRetornoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BoraViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRetornoBinding.inflate(inflater, container, false)
        val view = binding.root

        lifecycleScope.launch {
            viewModel.currentShift.collect { currentShift ->
                Log.i(
                    "TempoShift",
                    "currentshift do viewmodel na tela do almoço = ${currentShift.id}"
                )
                binding.tvTimeTelaAlmocoFinal.text =
                    currentShift.lunchEnd.format(DateTimeFormatter.ofPattern("HH:mm"))
            }
        }

        val timePicker: TimePickerDialog
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        timePicker =
            TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    var hourFormatted = "" + hourOfDay
                    if (hourOfDay < 10) {
                        hourFormatted = "0$hourFormatted"
                    }
                    var minuteFormatted = "" + minute
                    if (hourOfDay < 10) {
                        minuteFormatted = "0$minuteFormatted"
                    }
                    binding.tvTimeTelaAlmocoFinal.text = "$hourFormatted:$minuteFormatted"
                }
            }, hour, minute, true)

        binding.apply {
            ibLunchEnd.setOnClickListener {
                timePicker.show()
            }
            btNavigateEnd.setOnClickListener {
                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                val localTime = LocalTime.parse(binding.tvTimeTelaAlmocoFinal.text, timeFormatter)
                val currentDate = LocalDateTime.now().toLocalDate()
                val localDateTime = LocalDateTime.of(currentDate, localTime)
                val localCurrentShift = AppShift(
                    id = viewModel.currentShift.value.id,
                    start = viewModel.currentShift.value.start,
                    lunch = viewModel.currentShift.value.lunch,
                    lunchEnd = localDateTime,
                    pauses = viewModel.currentShift.value.pauses,
                    end = viewModel.currentShift.value.end,
                    isFinished = viewModel.currentShift.value.isFinished
                )

                viewModel.setCurrentShift(localCurrentShift)
                viewModel.updateShift(localCurrentShift)
//                val action = EntradaFragmentDirections
//                    .actionEntradaFragmentToAlmocoFragment()
//                view.findNavController().navigate(action)
            }
        }

        return view
    }
}