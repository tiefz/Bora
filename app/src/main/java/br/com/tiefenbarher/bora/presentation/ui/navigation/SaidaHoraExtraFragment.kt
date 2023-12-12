package br.com.tiefenbarher.bora.presentation.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.tiefenbarher.bora.databinding.FragmentAlmocoBinding
import br.com.tiefenbarher.bora.databinding.FragmentRetornoAlmocoBinding

class SaidaHoraExtraFragment : Fragment() {
    private var _binding: FragmentSaidaHoraExtraBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSaidaHoraExtraBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            ibLunchEnd.setOnClickListener {
                val action = RetornoAlmocoFragmentDirections
                    .actionRetornoAlmocoToHomeFragment()

                view.findNavController().navigate(action)
            }
        }

        return view
    }
}