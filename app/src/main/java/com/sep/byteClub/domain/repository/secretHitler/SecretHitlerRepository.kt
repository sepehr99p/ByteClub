package com.sep.byteClub.domain.repository.secretHitler

import com.sep.byteClub.domain.entity.secretHitler.SecretHitlerPlayerEntity
import kotlinx.coroutines.flow.Flow

interface SecretHitlerRepository {

    suspend fun setPlayers(list: List<SecretHitlerPlayerEntity>)

    suspend fun fetchLastGamePlayers() : Flow<List<SecretHitlerPlayerEntity>>

}