package br.com.tiefenbarher.bora.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.tiefenbarher.bora.R
import br.com.tiefenbarher.bora.databinding.ActivityTelaStartBinding

class TelaEntrada : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTelaStartBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_tela_start)
        binding.etInputHour.setText(intent.getStringExtra("variavel"))
    }
}