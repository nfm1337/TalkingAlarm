package ru.nfm.talkingalarm.domain

import javax.inject.Inject

class EditAlarmUseCase @Inject constructor(
    private val repository: AlarmRepository
) {

    suspend operator fun invoke(alarm: Alarm) = repository.editAlarm(alarm)
}