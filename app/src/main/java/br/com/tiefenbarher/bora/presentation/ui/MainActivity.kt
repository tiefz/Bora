package br.com.tiefenbarher.bora.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.tiefenbarher.bora.R
import br.com.tiefenbarher.bora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.ibClockStart.setOnClickListener {
            val intent = Intent(this, TelaEntrada::class.java)
            intent.putExtra("variavel", "13:45")
            startActivity(intent)
            finish()
        }
    }
}