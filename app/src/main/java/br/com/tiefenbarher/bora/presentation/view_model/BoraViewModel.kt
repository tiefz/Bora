package br.com.tiefenbarher.bora.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import br.com.tiefenbarher.bora.data.model.LocalShift
import br.com.tiefenbarher.bora.domain.model.AppShift
import br.com.tiefenbarher.bora.domain.model.repository.BoraRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    private val _startTime = MutableLiveData<String>()
    val startTime: LiveData<String>
        get() = _startTime

    val lunchTime: LiveData<String>
        get() = _lunchTime

    private val _lunchTime = MutableLiveData<String>()
    val returnTime: LiveData<String>
        get() = _returnTime

    private val _returnTime = MutableLiveData<String>()

    val currentShift: StateFlow<AppShift> = _currentShift

    fun setCurrentShift(appShift: AppShift) {
        _currentShift.value = appShift
    }

    init {
        viewModelScope.launch {
            _currentShift.map { shift ->
                shift.start.format(DateTimeFormatter.ofPattern("HH:mm"))
            }.collect { formattedTime ->
                _startTime.value = formattedTime
            }
        }
        viewModelScope.launch {
            _currentShift.map { shift ->
                shift.lunch.format(DateTimeFormatter.ofPattern("HH:mm"))
            }.collect { formattedTime ->
                _lunchTime.value = formattedTime
            }
        }
        viewModelScope.launch {
            _currentShift.map { shift ->
                shift.lunchEnd.format(DateTimeFormatter.ofPattern("HH:mm"))
            }.collect { formattedTime ->
                _returnTime.value = formattedTime
            }
        }
    }
}