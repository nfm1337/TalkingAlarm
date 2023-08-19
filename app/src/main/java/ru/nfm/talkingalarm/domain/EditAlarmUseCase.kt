package ru.nfm.talkingalarm.domain

import ru.nfm.talkingalarm.domain.model.Alarm
import ru.nfm.talkingalarm.domain.repository.AlarmRepository
import javax.inject.Inject

class EditAlarmUseCase @Inject constructor(
    private val repository: AlarmRepository
) {

    suspend operator fun invoke(alarm: Alarm) = repository.editAlarm(alarm)
}