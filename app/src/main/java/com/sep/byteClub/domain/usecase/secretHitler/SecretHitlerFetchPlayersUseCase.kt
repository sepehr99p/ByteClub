package com.sep.byteClub.domain.usecase.secretHitler

import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.domain.repository.secretHitler.SecretHitlerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SecretHitlerFetchPlayersUseCase @Inject constructor(
    private val secretHitlerRepository: SecretHitlerRepository
) {
    suspend operator fun invoke(): Flow<List<SecretHitlerPlayerEntity>> =
        secretHitlerRepository.fetchLastGamePlayers()
}