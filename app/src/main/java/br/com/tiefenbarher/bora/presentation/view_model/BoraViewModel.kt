package br.com.tiefenbarher.bora.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.tiefenbarher.bora.data.model.LocalShift
import br.com.tiefenbarher.bora.domain.model.AppShift
import br.com.tiefenbarher.bora.domain.model.repository.BoraRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class BoraViewModel(
    private val repository: BoraRepository
) : ViewModel() {

    fun saveShift(shift: AppShift) {
        viewModelScope.launch {
            repository.saveShift(shift)
        }
    }

    fun deleteShift(shift: AppShift) {
        viewModelScope.launch {
            repository.deleteShift(shift)
        }
    }

    fun getAllShifts(): Flow<List<LocalShift>> = repository.getAllShifts()

    var horario = "00:00"
    private val _currentShift = MutableStateFlow(
        AppShift(
            0L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),
            listOf(), LocalDateTime.now(), false
        )
    )
    val currentShift: StateFlow<AppShift> = _currentShift

    fun setCurrentShift(appShift: AppShift) {
        _currentShift.value = appShift
    }
}