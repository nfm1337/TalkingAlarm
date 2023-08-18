package ru.nfm.talkingalarm.domain

import javax.inject.Inject

class GetAlarmListUseCase @Inject constructor(
    private val repository: AlarmRepository
) {

    operator fun invoke() = repository.getAlarmList()
}