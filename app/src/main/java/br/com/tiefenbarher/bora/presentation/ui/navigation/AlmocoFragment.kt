package br.com.tiefenbarher.bora.presentation.ui.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import br.com.tiefenbarher.bora.databinding.FragmentAlmocoBinding
import br.com.tiefenbarher.bora.presentation.view_model.BoraViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.time.format.DateTimeFormatter

class AlmocoFragment : Fragment() {
    private var _binding: FragmentAlmocoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BoraViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlmocoBinding.inflate(inflater, container, false)
        val view = binding.root

        lifecycleScope.launch {
            viewModel.currentShift.collect { currentShift ->
                Log.i(
                    "TempoShift",
                    "currentshift do viewmodel na tela do almoço = ${currentShift.id}"
                )
                binding.tvTimeTelaAlmocoInicial.text =
                    currentShift.start.format(DateTimeFormatter.ofPattern("HH:mm"))
            }
        }

        binding.apply {
            ibLunchStart.setOnClickListener {
                val action = AlmocoFragmentDirections
                    .actionAlmocoFragmentToHomeFragment()

                view.findNavController().navigate(action)
            }
        }

        return view
    }
}