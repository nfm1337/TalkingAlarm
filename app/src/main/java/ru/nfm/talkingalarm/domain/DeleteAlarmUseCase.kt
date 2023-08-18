package ru.nfm.talkingalarm.domain

import javax.inject.Inject

class DeleteAlarmUseCase @Inject constructor(
    private val repository: AlarmRepository
) {

    suspend operator fun invoke(id: Long) = repository.deleteAlarm(id)
}