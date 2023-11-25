package br.com.tiefenbarher.bora.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interval")
data class Interval(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val start: String,
    val end: String
)