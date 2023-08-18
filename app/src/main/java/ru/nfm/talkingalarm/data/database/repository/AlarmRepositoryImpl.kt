package ru.nfm.talkingalarm.data.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.nfm.talkingalarm.data.database.dao.AlarmDao
import ru.nfm.talkingalarm.data.mapper.AlarmMapper
import ru.nfm.talkingalarm.domain.Alarm
import ru.nfm.talkingalarm.domain.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val alarmDao: AlarmDao,
    private val mapper: AlarmMapper
) : AlarmRepository {

    override suspend fun getAlarmInfo(id: Long): Alarm {
        return mapper.mapDbModelToDomain(alarmDao.getAlarm(id))
    }

    override fun getAlarmList(): LiveData<List<Alarm>> {
        return alarmDao.getAlarmList().map { mapper.mapListDbModelToDomainList(it) }
    }

    override suspend fun deleteAlarm(id: Long) {
        alarmDao.deleteAlarm(id)
    }

    override suspend fun createAlarm(alarm: Alarm) {
        alarmDao.insertOrUpdateAlarm(mapper.mapDomainToDbModel(alarm))
    }

    override suspend fun editAlarm(alarm: Alarm) {
        createAlarm(alarm)
    }
}