package br.com.tiefenbarher.bora.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import br.com.tiefenbarher.bora.R
import br.com.tiefenbarher.bora.data.dao.BoraDao
import br.com.tiefenbarher.bora.data.db.BoraDatabase
import br.com.tiefenbarher.bora.data.model.Interval
import br.com.tiefenbarher.bora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        startDatabase()
    }

    fun startDatabase() {
        val db = Room.databaseBuilder(
            applicationContext,
            BoraDatabase::class.java,
            "BoraDatabase"
        ).build()
        val dao = db.boraDao()
        saveInterval(dao)
    }

    fun saveInterval(dao: BoraDao) {
        val interval = Interval(start = "00:00", end = "00:01")
        dao.saveInterval(interval)
    }
}