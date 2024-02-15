package br.com.tiefenbarher.bora.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import br.com.tiefenbarher.bora.data.model.LocalShift
import br.com.tiefenbarher.bora.domain.model.AppShift
import br.com.tiefenbarher.bora.domain.model.repository.BoraRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class BoraViewModel(
    private val repository: BoraRepository
) : ViewModel() {

    fun updateShift(shift: AppShift) {
        viewModelScope.launch {
            repository.saveShift(shift)
        }
    }

    fun deleteShift(shift: AppShift) {
        viewModelScope.launch {
            repository.deleteShift(shift)
        }
    }

    val shifts: LiveData<List<LocalShift>> = repository.getAllShifts().asLiveData()

    var horario = "00:00"
    private val _currentShift = MutableStateFlow(
        AppShift(
            1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),
            listOf(), LocalDateTime.now(), false
        )
    )
    val currentShift: StateFlow<AppShift> = _currentShift

    fun setCurrentShift(appShift: AppShift) {
        _currentShift.value = appShift
    }
}