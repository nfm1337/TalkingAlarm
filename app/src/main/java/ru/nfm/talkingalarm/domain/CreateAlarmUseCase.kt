package ru.nfm.talkingalarm.domain

import ru.nfm.talkingalarm.domain.model.Alarm
import ru.nfm.talkingalarm.domain.repository.AlarmRepository
import javax.inject.Inject

class CreateAlarmUseCase @Inject constructor(
    private val repository: AlarmRepository
) {

    suspend operator fun invoke(alarm: Alarm) = repository.createAlarm(alarm)
}