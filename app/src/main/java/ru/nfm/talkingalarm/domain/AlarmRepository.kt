package ru.nfm.talkingalarm.domain

import androidx.lifecycle.LiveData

interface AlarmRepository {

    fun getAlarmList(): LiveData<List<Alarm>>

    suspend fun getAlarmInfo(id: Long): Alarm

    suspend fun deleteAlarm(id: Long)

    suspend fun createAlarm(alarm: Alarm)

    suspend fun editAlarm(alarm: Alarm)
}