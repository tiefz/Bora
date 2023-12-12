package br.com.tiefenbarher.bora.data.repository

import br.com.tiefenbarher.bora.data.dao.BoraDao
import br.com.tiefenbarher.bora.domain.model.AppShift
import br.com.tiefenbarher.bora.domain.model.repository.BoraRepository

class BoraRepositoryImpl(
    private val dao: BoraDao
) : BoraRepository {
    override suspend fun saveShift(shift: AppShift) {
        dao.saveShift(shift.fromAppModel())
    }
}