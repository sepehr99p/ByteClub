package com.sep.quiz.data.repository.secretHitler

import com.sep.quiz.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.quiz.domain.repository.secretHitler.SecretHitlerRepository

class SecretHitlerRepositoryImpl : SecretHitlerRepository {
    override suspend fun setPlayers(list: List<SecretHitlerPlayerEntity>) {
        //todo : impl later with dataStore
    }

    override suspend fun fetchLastGamePlayers(): List<String> {
        //todo : impl later with dataStore
        return emptyList()
    }
}