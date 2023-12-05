package br.com.tiefenbarher.bora.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.tiefenbarher.bora.data.dao.BoraDao
import br.com.tiefenbarher.bora.data.model.Interval
import br.com.tiefenbarher.bora.data.model.LocalShift
import br.com.tiefenbarher.bora.presentation.utils.Converter

@Database(entities = [Interval::class, LocalShift::class], version = 2)
@TypeConverters(Converter::class)
abstract class BoraDatabase : RoomDatabase() {
    abstract fun boraDao(): BoraDao
}