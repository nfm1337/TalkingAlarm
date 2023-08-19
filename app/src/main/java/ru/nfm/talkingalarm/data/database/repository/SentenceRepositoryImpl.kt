package ru.nfm.talkingalarm.data.database.repository

import ru.nfm.talkingalarm.data.database.dao.SentenceDao
import ru.nfm.talkingalarm.data.mapper.SentenceMapper
import ru.nfm.talkingalarm.domain.model.Sentence
import ru.nfm.talkingalarm.domain.repository.SentenceRepository
import javax.inject.Inject

class SentenceRepositoryImpl @Inject constructor(
    private val sentenceDao: SentenceDao,
    private val mapper: SentenceMapper
) : SentenceRepository {

    override suspend fun getSentence(id: Long): Sentence {
        return mapper.mapDbModelToDomain(sentenceDao.getSentence(id))
    }
}