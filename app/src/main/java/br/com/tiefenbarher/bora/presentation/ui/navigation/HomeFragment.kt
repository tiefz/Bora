package br.com.tiefenbarher.bora.presentation.ui.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.tiefenbarher.bora.data.dao.BoraDao
import br.com.tiefenbarher.bora.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val dao: BoraDao by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment

        fun saveInterval(dao: BoraDao) {
            val interval = dao.getIntervalById(3)
            Log.i(
                "TesteBanco",
                "Hora de inicio: ${interval.start} - hora de termino: ${interval.end}"
            )
        }

        binding.apply {
            ibClockStart.setOnClickListener {
                val action = HomeFragmentDirections
                    .actionHomeFragmentToEntradaFragment()

                //view.findNavController().navigate(action)
                CoroutineScope(Dispatchers.IO).launch {
                    saveInterval(dao)
                }
            }
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}