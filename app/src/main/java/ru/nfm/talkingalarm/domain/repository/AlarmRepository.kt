package ru.nfm.talkingalarm.domain.repository

import androidx.lifecycle.LiveData
import ru.nfm.talkingalarm.domain.model.Alarm

interface AlarmRepository {

    fun getAlarmList(): LiveData<List<Alarm>>

    suspend fun getAlarmInfo(id: Long): Alarm

    suspend fun deleteAlarm(id: Long)

    suspend fun createAlarm(alarm: Alarm)

    suspend fun editAlarm(alarm: Alarm)
}