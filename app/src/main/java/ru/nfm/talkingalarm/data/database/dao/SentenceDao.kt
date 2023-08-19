package ru.nfm.talkingalarm.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import ru.nfm.talkingalarm.data.database.model.SentenceDbModel

@Dao
interface SentenceDao {

    @Query("SELECT * FROM sentences WHERE id=:id")
    suspend fun getSentence(id: Long): SentenceDbModel
}