package br.com.tiefenbarher.bora.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.tiefenbarher.bora.data.model.Interval
import br.com.tiefenbarher.bora.data.model.LocalShift

@Dao
interface BoraDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveShift(shift: LocalShift)

    @Query("SELECT * FROM interval")
    fun getAllIntervals(): List<Interval>

    @Query("SELECT * FROM interval WHERE id = :id")
    fun getIntervalById(id: Long): Interval

    @Delete
    fun deleteInterval(interval: Interval)
}