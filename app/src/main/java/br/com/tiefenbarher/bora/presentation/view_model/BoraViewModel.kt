package br.com.tiefenbarher.bora.presentation.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.tiefenbarher.bora.data.model.LocalShift
import br.com.tiefenbarher.bora.domain.model.AppShift
import br.com.tiefenbarher.bora.domain.model.repository.BoraRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class BoraViewModel(
    private val repository: BoraRepository
) : ViewModel() {

    init {
        fetchShifts()
    }

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

    var shiftList = mutableListOf<LocalShift>()
    //fun getAllShifts(): Flow<List<LocalShift>> = repository.getAllShifts()

    private fun fetchShifts() {
        viewModelScope.launch {
            repository.getAllShifts().flowOn(Dispatchers.IO)
                .catch { e ->
                    Log.i("Database Error", e.message.toString())
                }
                .collect {
                    shiftList.addAll(it)
                }
        }
    }

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