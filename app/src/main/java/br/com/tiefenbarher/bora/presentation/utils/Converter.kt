package br.com.tiefenbarher.bora.presentation.utils

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converter {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.toString()
    }


    @TypeConverter
    fun fromString(value: String?): List<LocalDateTime>? {
        return value?.let {
            if (!it.isNullOrEmpty()) {
                it.split(",").map { timestamp ->
                    LocalDateTime.parse(timestamp, formatter)
                }
            } else {
                emptyList()
            }
        }
    }

    @TypeConverter
    fun toString(value: List<LocalDateTime>?): String? {
        return value?.joinToString(",") { timestamp ->
            timestamp.format(formatter)
        }
    }
}