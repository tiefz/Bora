package br.com.tiefenbarher.bora.presentation.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.tiefenbarher.bora.databinding.FragmentSaidaBinding
import br.com.tiefenbarher.bora.presentation.view_model.BoraViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SaidaFragment : Fragment() {
    private var _binding: FragmentSaidaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BoraViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSaidaBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment

        binding.apply {
            viewModel = viewModel
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
        }

        return view
    }
}