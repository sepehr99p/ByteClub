package com.sep.byteClub.domain.usecase.crypto

import com.sep.byteClub.domain.entity.crypto.TickerEntity
import com.sep.byteClub.domain.repository.crypto.KucoinRepository
import com.sep.byteClub.utils.ResultState
import javax.inject.Inject

class TickerListUseCase @Inject constructor(private val kucoinRepository: KucoinRepository) {
    suspend operator fun invoke(): ResultState<List<TickerEntity>> =
        kucoinRepository.fetchAllTickers()
}