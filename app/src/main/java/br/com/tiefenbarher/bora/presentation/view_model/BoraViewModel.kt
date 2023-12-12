package br.com.tiefenbarher.bora.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.tiefenbarher.bora.domain.model.AppShift
import br.com.tiefenbarher.bora.domain.model.repository.BoraRepository
import kotlinx.coroutines.launch

class BoraViewModel(
    private val repository: BoraRepository
) : ViewModel() {

    fun saveShift(shift: AppShift) {
        viewModelScope.launch {
            repository.saveShift(shift)
        }
    }

    var horario = "00:00"
}