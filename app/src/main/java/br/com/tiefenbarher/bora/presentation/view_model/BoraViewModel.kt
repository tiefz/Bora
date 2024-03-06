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
import java.time.LocalTime
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

    fun startOver() {
        deleteShift(currentShift.value)
    }

    fun calculateExit() {
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val currentDate = LocalDateTime.now().toLocalDate()

        val startTimeConverted = LocalTime.parse(startTime.value, timeFormatter)
        val shiftTimeConverted = LocalTime.parse(shiftTime.value, timeFormatter)
        val returnTimeConverted = LocalTime.parse(returnTime.value, timeFormatter)
        val lunchTimeConverted = LocalTime.parse(lunchTime.value, timeFormatter)

        val totalShiftTime = shiftTimeConverted.plusHours(startTimeConverted.hour.toLong())
            .plusMinutes(startTimeConverted.minute.toLong())
        val totalPauseTime = returnTimeConverted.minusHours(lunchTimeConverted.hour.toLong())
            .minusMinutes(lunchTimeConverted.minute.toLong())
        val endTimeConverted = LocalDateTime.of(
            currentDate,
            totalShiftTime.plusHours(totalPauseTime.hour.toLong())
                .plusMinutes(totalPauseTime.minute.toLong())
        )
        _endTime.value = endTimeConverted.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    val shifts: LiveData<List<LocalShift>> = repository.getAllShifts().asLiveData()

    private val _currentShift = MutableStateFlow(
        AppShift(
            1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),
            listOf(), LocalDateTime.now(), false
        )
    )

    private val _shiftTime = MutableLiveData<String>("08:00")
    val shiftTime: LiveData<String>
        get() = _shiftTime

    private val _startTime = MutableLiveData<String>()
    val startTime: LiveData<String>
        get() = _startTime

    val lunchTime: LiveData<String>
        get() = _lunchTime

    private val _lunchTime = MutableLiveData<String>()
    val returnTime: LiveData<String>
        get() = _returnTime

    private val _returnTime = MutableLiveData<String>()

    val endTime: LiveData<String>
        get() = _endTime

    private val _endTime = MutableLiveData<String>()

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