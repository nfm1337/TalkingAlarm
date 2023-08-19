package ru.nfm.talkingalarm.data.mapper

import ru.nfm.talkingalarm.data.database.model.SentenceDbModel
import ru.nfm.talkingalarm.domain.model.Sentence
import javax.inject.Inject

class SentenceMapper @Inject constructor() {

    fun mapDbModelToDomain(dbModel: SentenceDbModel) : Sentence {
        return Sentence(
            id = dbModel.id,
            sentence = dbModel.sentence
        )
    }
}