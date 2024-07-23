package com.sep.quiz.domain.usecase.crypto

import com.sep.quiz.domain.entiry.crypto.SingleTickerEntity
import com.sep.quiz.domain.repository.crypto.KucoinRepository
import com.sep.quiz.utils.ResultState
import javax.inject.Inject

class FetchTickerUseCase @Inject constructor(private val kucoinRepository: KucoinRepository) {
    suspend operator fun invoke(symbol: String): ResultState<SingleTickerEntity> =
        kucoinRepository.fetchTicker(symbol)
}