package com.sep.quiz.domain.usecase.crypto

import com.sep.quiz.domain.entiry.crypto.CandleEntity
import com.sep.quiz.domain.repository.KucoinRepository
import com.sep.quiz.utils.ResultState
import javax.inject.Inject

class CandlesUseCase @Inject constructor(private val kucoinRepository: KucoinRepository) {
    suspend operator fun invoke(interval: String, symbol: String): ResultState<List<CandleEntity>> =
        kucoinRepository.fetchCandles(interval = interval, symbol = symbol)
}