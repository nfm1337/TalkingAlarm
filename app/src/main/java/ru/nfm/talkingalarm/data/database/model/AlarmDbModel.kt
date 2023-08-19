package ru.nfm.talkingalarm.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class AlarmDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = UNDEFINED_ID,
    val time: Long,
    val name: String = EMPTY_STRING,
    val soundUri: String,
    val isActive: Boolean,
    val repeatDays: Int = NO_REPEAT
) {

    companion object {
        private const val UNDEFINED_ID = 0L
        private const val NO_REPEAT = 0
        private const val EMPTY_STRING = ""
    }
}