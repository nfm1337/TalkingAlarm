package ru.nfm.talkingalarm.presentation

import androidx.lifecycle.ViewModel
import ru.nfm.talkingalarm.domain.CreateAlarmUseCase
import ru.nfm.talkingalarm.domain.EditAlarmUseCase
import ru.nfm.talkingalarm.domain.GetAlarmInfoUseCase
import javax.inject.Inject

class AlarmItemViewModel @Inject constructor(
    private val getAlarmInfoUseCase: GetAlarmInfoUseCase,
    private val editAlarmUseCase: EditAlarmUseCase,
    private val createAlarmUseCase: CreateAlarmUseCase
): ViewModel() {

}