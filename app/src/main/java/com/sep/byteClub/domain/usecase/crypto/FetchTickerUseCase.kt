package com.sep.byteClub.domain.usecase.crypto

import com.sep.byteClub.domain.entity.crypto.SingleTickerEntity
import com.sep.byteClub.domain.repository.crypto.KucoinRepository
import com.sep.byteClub.utils.ResultState
import javax.inject.Inject

class FetchTickerUseCase @Inject constructor(private val kucoinRepository: KucoinRepository) {
    suspend operator fun invoke(symbol: String): ResultState<SingleTickerEntity> =
        kucoinRepository.fetchTicker(symbol)
}