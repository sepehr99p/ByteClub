package com.sep.quiz.domain.repository.secretHitler

import com.sep.quiz.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import kotlinx.coroutines.flow.Flow

interface SecretHitlerRepository {

    suspend fun setPlayers(list: List<SecretHitlerPlayerEntity>)

    suspend fun fetchLastGamePlayers() : Flow<List<SecretHitlerPlayerEntity>>

}