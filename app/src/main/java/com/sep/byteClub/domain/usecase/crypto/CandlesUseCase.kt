package com.sep.byteClub.domain.usecase.crypto

import com.sep.byteClub.domain.entity.crypto.CandleEntity
import com.sep.byteClub.domain.repository.crypto.KucoinRepository
import com.sep.byteClub.utils.ResultState
import javax.inject.Inject

class CandlesUseCase @Inject constructor(private val kucoinRepository: KucoinRepository) {
    suspend operator fun invoke(interval: String, symbol: String): ResultState<List<CandleEntity>> =
        kucoinRepository.fetchCandles(interval = interval, symbol = symbol)
}