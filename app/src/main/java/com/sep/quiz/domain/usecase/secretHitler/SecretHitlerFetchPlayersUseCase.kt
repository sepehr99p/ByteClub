package com.sep.quiz.domain.usecase.secretHitler

import com.sep.quiz.domain.repository.secretHitler.SecretHitlerRepository
import javax.inject.Inject

class SecretHitlerFetchPlayersUseCase @Inject constructor(
    private val secretHitlerRepository: SecretHitlerRepository
) {
    suspend operator fun invoke() = secretHitlerRepository.fetchLastGamePlayers()
}