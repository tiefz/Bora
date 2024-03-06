package br.com.tiefenbarher.bora.presentation.ui.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.tiefenbarher.bora.databinding.FragmentSaidaBinding
import br.com.tiefenbarher.bora.presentation.view_model.BoraViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

private const val END_TIME = "end_time"

class SaidaFragment : Fragment() {
    private var _binding: FragmentSaidaBinding? = null
    private val binding get() = _binding!!
    private val boraViewModel: BoraViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSaidaBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment

        binding.apply {
            viewModel = boraViewModel
            resumoEntrada.setOnClickListener {
                val action = SaidaFragmentDirections
                    .actionSaidaFragmentToEntradaFragment()
                view.findNavController().navigate(action)
            }
            resumoAlmocoS.setOnClickListener {
                val action = SaidaFragmentDirections
                    .actionSaidaFragmentToAlmocoFragment()
                view.findNavController().navigate(action)
            }
            resumoAlmocoR.setOnClickListener {
                val action = SaidaFragmentDirections
                    .actionSaidaFragmentToRetornoFragment()
                view.findNavController().navigate(action)
            }
            btStartOver.setOnClickListener {
                boraViewModel.startOver()
                val action = SaidaFragmentDirections
                    .actionSaidaFragmentToHomeFragment()
                view.findNavController().navigate(action)
            }
        }
        boraViewModel.calculateExit()
        boraViewModel.endTime.value?.let { savePrefs(it) }

        return view
    }

    private fun savePrefs(endTime: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(END_TIME, endTime)
            apply()
        }
    }
}