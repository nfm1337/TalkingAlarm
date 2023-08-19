package ru.nfm.talkingalarm.domain.repository

import ru.nfm.talkingalarm.domain.model.Sentence

interface SentenceRepository {

    suspend fun getSentence(id: Long) : Sentence
}