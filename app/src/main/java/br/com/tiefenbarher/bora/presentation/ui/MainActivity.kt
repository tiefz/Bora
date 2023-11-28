package br.com.tiefenbarher.bora.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.tiefenbarher.bora.R
import br.com.tiefenbarher.bora.data.dao.BoraDao
import br.com.tiefenbarher.bora.databinding.ActivityMainBinding
import br.com.tiefenbarher.bora.di.boraModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    private val dao: BoraDao by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        startKoin {
            androidContext(this@MainActivity)
            modules(boraModule)
        }
    }
}