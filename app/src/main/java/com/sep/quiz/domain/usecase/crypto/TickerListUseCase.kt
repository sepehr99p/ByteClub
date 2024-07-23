package com.sep.quiz.domain.usecase.crypto

import com.sep.quiz.domain.entiry.crypto.TickerEntity
import com.sep.quiz.domain.repository.crypto.KucoinRepository
import com.sep.quiz.utils.ResultState
import javax.inject.Inject

class TickerListUseCase @Inject constructor(private val kucoinRepository: KucoinRepository) {
    suspend operator fun invoke(): ResultState<List<TickerEntity>> =
        kucoinRepository.fetchAllTickers()
}