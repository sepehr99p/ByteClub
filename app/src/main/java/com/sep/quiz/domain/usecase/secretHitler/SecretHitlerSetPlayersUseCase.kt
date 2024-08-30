package com.sep.quiz.domain.usecase.secretHitler

import com.sep.quiz.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.quiz.domain.repository.secretHitler.SecretHitlerRepository
import javax.inject.Inject

class SecretHitlerSetPlayersUseCase @Inject constructor(
    private val secretHitlerRepository: SecretHitlerRepository
) {

    suspend operator fun invoke(list: List<SecretHitlerPlayerEntity>) =
        secretHitlerRepository.setPlayers(list = list)

}