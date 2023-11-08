package br.com.tiefenbarher.bora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.tiefenbarher.bora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {

            btBotao1.setOnClickListener {
                tvHello.text = "teste"
            }
            btBotao2.setOnClickListener {
                tvHello.text = "teste do botao 2"
            }

        }
    }
}