package ru.nfm.talkingalarm.domain

import ru.nfm.talkingalarm.domain.repository.SentenceRepository
import javax.inject.Inject

class GetSentenceUseCase @Inject constructor(
    private val repository: SentenceRepository
) {

    suspend operator fun invoke(id: Long) = repository.getSentence(id)
}