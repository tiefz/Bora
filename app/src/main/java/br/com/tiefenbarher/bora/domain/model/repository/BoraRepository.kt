package br.com.tiefenbarher.bora.domain.model.repository

import br.com.tiefenbarher.bora.data.model.LocalShift
import br.com.tiefenbarher.bora.domain.model.AppShift
import kotlinx.coroutines.flow.Flow

interface BoraRepository {
    suspend fun saveShift(shift: AppShift)
    fun getAllShifts(): Flow<List<LocalShift>>
    suspend fun deleteShift(shift: AppShift)
}