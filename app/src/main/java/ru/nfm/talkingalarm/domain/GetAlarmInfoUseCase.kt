package ru.nfm.talkingalarm.domain

import javax.inject.Inject

class GetAlarmInfoUseCase @Inject constructor(
    private val repository: AlarmRepository
) {

    suspend operator fun invoke(id: Long) = repository.getAlarmInfo(id)
}