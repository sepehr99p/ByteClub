package com.sep.quiz.domain.repository.secretHitler

import com.sep.quiz.domain.entiry.secretHitler.SecretHitlerPlayerEntity

interface SecretHitlerRepository {

    suspend fun setPlayers(list: List<SecretHitlerPlayerEntity>)

    suspend fun fetchLastGamePlayers() : List<String>

}