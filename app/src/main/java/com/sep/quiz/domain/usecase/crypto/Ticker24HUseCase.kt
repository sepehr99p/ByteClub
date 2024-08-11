package com.sep.quiz.domain.usecase.crypto

import com.sep.quiz.domain.repository.crypto.KucoinRepository
import javax.inject.Inject

class Ticker24HUseCase @Inject constructor(
    private val kucoinRepository: KucoinRepository
) {

    suspend operator fun invoke(symbol: String) = kucoinRepository.fetch24HTicker(symbol = symbol)

}