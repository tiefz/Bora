package br.com.tiefenbarher.bora.presentation.view_model

import androidx.lifecycle.ViewModel
import br.com.tiefenbarher.bora.domain.model.AppShift
import br.com.tiefenbarher.bora.domain.model.repository.BoraRepository

class BoraViewModel(
    private val repository: BoraRepository
) : ViewModel() {

    fun saveShift(shift: AppShift) {
        repository.saveShift(shift)
    }

    var horario = "00:00"
}