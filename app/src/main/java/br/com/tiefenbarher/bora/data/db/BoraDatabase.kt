package br.com.tiefenbarher.bora.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.tiefenbarher.bora.data.dao.BoraDao
import br.com.tiefenbarher.bora.data.model.Interval

@Database(entities = [Interval::class], version = 1)
abstract class BoraDatabase : RoomDatabase() {
    abstract fun boraDao(): BoraDao
}