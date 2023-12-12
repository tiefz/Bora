package br.com.tiefenbarher.bora.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.tiefenbarher.bora.domain.model.AppShift
import java.time.LocalDateTime

@Entity(tableName = "shift")
data class LocalShift(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val start: LocalDateTime,
    val lunch: LocalDateTime,
    val lunchEnd: LocalDateTime,
    val pauses: List<LocalDateTime>,
    val end: LocalDateTime,
    val isFinished: Boolean
) {
    companion object {
        fun fromAppModel(appShift: AppShift): LocalShift {
            return LocalShift(
                id = appShift.id,
                start = appShift.start,
                lunch = appShift.lunch,
                lunchEnd = appShift.lunchEnd,
                pauses = appShift.pauses,
                end = appShift.end,
                isFinished = appShift.isFinished
            )
        }
    }

    fun toAppModel(): AppShift {
        return AppShift(
            id = this.id,
            start = this.start,
            lunch = this.lunch,
            lunchEnd = this.lunchEnd,
            pauses = this.pauses,
            end = this.end,
            isFinished = this.isFinished
        )
    }
}


