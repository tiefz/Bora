package br.com.tiefenbarher.bora.domain.model.repository

import br.com.tiefenbarher.bora.domain.model.AppShift

interface BoraRepository {
    fun saveShift(shift: AppShift)
}