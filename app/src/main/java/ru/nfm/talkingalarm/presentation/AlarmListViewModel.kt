package ru.nfm.talkingalarm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nfm.talkingalarm.domain.Alarm
import ru.nfm.talkingalarm.domain.DeleteAlarmUseCase
import ru.nfm.talkingalarm.domain.EditAlarmUseCase
import ru.nfm.talkingalarm.domain.GetAlarmListUseCase
import javax.inject.Inject

class AlarmListViewModel @Inject constructor(
    private val getAlarmListUseCase: GetAlarmListUseCase,
    private val deleteAlarmUseCase: DeleteAlarmUseCase,
    private val editAlarmUseCase: EditAlarmUseCase
): ViewModel() {

    val alarmList = getAlarmListUseCase()

    fun deleteAlarmItem(alarm: Alarm) {
        viewModelScope.launch {
            deleteAlarmUseCase(alarm.id)
        }
    }

    fun changeIsActiveState(alarm: Alarm) {
        viewModelScope.launch {
            val newItem = alarm.copy(isActive = !alarm.isActive)
            editAlarmUseCase(newItem)
        }
    }
}