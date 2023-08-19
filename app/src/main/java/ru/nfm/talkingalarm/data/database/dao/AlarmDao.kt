package ru.nfm.talkingalarm.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.nfm.talkingalarm.data.database.model.AlarmDbModel

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarms")
    fun getAlarmList(): LiveData<List<AlarmDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAlarm(alarm: AlarmDbModel)

    @Query("DELETE FROM alarms WHERE id=:id")
    suspend fun deleteAlarm(id: Long)

    @Query("SELECT * FROM alarms WHERE id=:id")
    suspend fun getAlarm(id: Long): AlarmDbModel
}