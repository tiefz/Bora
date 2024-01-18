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
import androidx.navigation.findNavController
import br.com.tiefenbarher.bora.databinding.FragmentEntradaBinding
import br.com.tiefenbarher.bora.domain.model.AppShift
import br.com.tiefenbarher.bora.presentation.view_model.BoraViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class EntradaFragment : Fragment() {

    private var _binding: FragmentEntradaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BoraViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntradaBinding.inflate(inflater, container, false)
        val view = binding.root

        Log.i(
            "TempoShift",
            "Viewmodel segunda atualizaçao do valor: ${viewModel.currentShift.value}"
        )


        lifecycleScope.launch {
            viewModel.currentShift.collect { currentShift ->
                Log.i("TempoShift", "currentshift do viewmodel = ${currentShift.id}")
                binding.tvInputHour.text =
                    currentShift.start.format(DateTimeFormatter.ofPattern("HH:mm"))
            }
        }

        val timePicker: TimePickerDialog
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        timePicker =
            TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    binding.tvInputHour.text = String.format("%d:%d", hourOfDay, minute)
                }
            }, hour, minute, true)

        binding.apply {
            ibStartCalculate.setOnClickListener {
                Log.i("Relogio", "Hora: $hour - Minutos: $minute")

                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                val localTime = LocalTime.parse(binding.tvInputHour.text, timeFormatter)
                val currentDate = LocalDateTime.now().toLocalDate()
                val localDateTime = LocalDateTime.of(currentDate, localTime)

                viewModel.setCurrentShift(
                    AppShift(
                        id = viewModel.currentShift.value.id,
                        start = localDateTime,
                        lunch = viewModel.currentShift.value.lunch,
                        lunchEnd = viewModel.currentShift.value.lunchEnd,
                        pauses = viewModel.currentShift.value.pauses,
                        end = viewModel.currentShift.value.end,
                        isFinished = viewModel.currentShift.value.isFinished
                    )
                )
                viewModel.saveShift(viewModel.currentShift.value)
                val action = EntradaFragmentDirections
                    .actionEntradaFragmentToAlmocoFragment()
                view.findNavController().navigate(action)
            }
            tvInputHour.setOnClickListener {
                timePicker.show()
            }
        }

        return view
    }
}