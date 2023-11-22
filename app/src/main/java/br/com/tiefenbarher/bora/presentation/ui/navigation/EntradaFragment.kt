package br.com.tiefenbarher.bora.presentation.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.tiefenbarher.bora.databinding.FragmentEntradaBinding

class EntradaFragment : Fragment() {

    private var _binding: FragmentEntradaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntradaBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            ibStartCalculate.setOnClickListener {
                val action = EntradaFragmentDirections
                    .actionEntradaFragmentToAlmocoFragment()
                view.findNavController().navigate(action)
            }
        }

        return view
    }
}