package com.sep.quiz.domain.usecase.crypto

import com.sep.quiz.domain.entiry.crypto.CurrencyEntity
import com.sep.quiz.domain.repository.KucoinRepository
import com.sep.quiz.utils.ResultState
import javax.inject.Inject

class CurrencyListUseCase @Inject constructor(private val kucoinRepository: KucoinRepository) {
    suspend operator fun invoke(): ResultState<List<CurrencyEntity>> =
        kucoinRepository.fetchCurrencyList()
}

