package br.com.tiefenbarher.bora.data.repository

import br.com.tiefenbarher.bora.data.dao.BoraDao
import br.com.tiefenbarher.bora.data.model.LocalShift
import br.com.tiefenbarher.bora.domain.model.AppShift
import br.com.tiefenbarher.bora.domain.model.repository.BoraRepository
import kotlinx.coroutines.flow.Flow

class BoraRepositoryImpl(
    private val dao: BoraDao
) : BoraRepository {
    override suspend fun saveShift(shift: AppShift) {
        dao.saveShift(shift.fromAppModel())
    }

    override fun getAllShifts(): Flow<List<LocalShift>> {
        return dao.getAllShifts()
    }

    override suspend fun deleteShift(shift: AppShift) {
        dao.deleteShift(shift.fromAppModel())
    }
}