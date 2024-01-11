package br.com.tiefenbarher.bora.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.tiefenbarher.bora.data.model.LocalShift
import br.com.tiefenbarher.bora.domain.model.AppShift
import br.com.tiefenbarher.bora.domain.model.repository.BoraRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BoraViewModel(
    private val repository: BoraRepository
) : ViewModel() {

    fun saveShift(shift: AppShift) {
        viewModelScope.launch {
            repository.saveShift(shift)
        }
    }

    fun getAllShifts(): Flow<List<LocalShift>> = repository.getAllShifts()

    var horario = "00:00"
}