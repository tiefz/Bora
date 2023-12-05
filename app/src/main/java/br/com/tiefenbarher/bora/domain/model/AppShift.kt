package br.com.tiefenbarher.bora.domain.model

import br.com.tiefenbarher.bora.data.model.LocalShift
import java.time.LocalDateTime

data class AppShift(
    val id: Long = 0,
    val start: LocalDateTime,
    val lunch: LocalDateTime,
    val lunchEnd: LocalDateTime,
    val pauses: List<LocalDateTime>,
    val end: LocalDateTime,
    val isFinished: Boolean
) {
    fun fromAppModel(): LocalShift {
        return LocalShift(
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
