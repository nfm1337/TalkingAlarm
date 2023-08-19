package ru.nfm.talkingalarm.presentation.alarmitem

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nfm.talkingalarm.domain.model.Alarm
import ru.nfm.talkingalarm.domain.CreateAlarmUseCase
import ru.nfm.talkingalarm.domain.EditAlarmUseCase
import ru.nfm.talkingalarm.domain.GetAlarmInfoUseCase
import javax.inject.Inject

class AlarmItemViewModel @Inject constructor(
    private val getAlarmInfoUseCase: GetAlarmInfoUseCase,
    private val editAlarmUseCase: EditAlarmUseCase,
    private val createAlarmUseCase: CreateAlarmUseCase
) : ViewModel() {

    private val _alarmItem = MutableLiveData<Alarm>()
    val alarmItem: LiveData<Alarm>
        get() = _alarmItem

    fun getAlarmItem(alarmId: Long) {
        viewModelScope.launch {
            val item = getAlarmInfoUseCase(alarmId)
            _alarmItem.value = item
        }
    }

    fun addAlarm(alarm: Alarm) {
        viewModelScope.launch {
            createAlarmUseCase(alarm)
            Log.d("AlarmItemViewModel", "Alarm created: $alarm")
        }
    }

    fun editAlarm(alarm: Alarm) {
        _alarmItem.value?.let {
            viewModelScope.launch {
                val item = it.copy(
                    name = it.name,
                    timeInMillis = it.timeInMillis,
                    repeatDays = it.repeatDays,
                    isActive = it.isActive,
                    soundUri = it.soundUri
                )
                editAlarmUseCase(item)
            }
        }
    }
}