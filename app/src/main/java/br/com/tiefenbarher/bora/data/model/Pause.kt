package br.com.tiefenbarher.bora.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pause")
data class Pause(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val start: String,
    val end: String
)