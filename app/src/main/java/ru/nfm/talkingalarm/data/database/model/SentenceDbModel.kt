package ru.nfm.talkingalarm.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sentences")
data class SentenceDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = NO_ID,
    val sentence: String
) {

    companion object {
        private const val NO_ID = 0L
    }
}