package br.com.tiefenbarher.bora.domain.model.repository

import br.com.tiefenbarher.bora.domain.model.AppShift

interface BoraRepository {
    suspend fun saveShift(shift: AppShift)
    suspend fun getAllShifts(): List<AppShift>
}