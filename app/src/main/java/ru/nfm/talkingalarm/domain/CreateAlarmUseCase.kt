package ru.nfm.talkingalarm.domain

import javax.inject.Inject

class CreateAlarmUseCase @Inject constructor(
    private val repository: AlarmRepository
) {

    suspend operator fun invoke(alarm: Alarm) = repository.createAlarm(alarm)
}