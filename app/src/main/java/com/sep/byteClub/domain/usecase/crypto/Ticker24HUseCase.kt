package com.sep.byteClub.domain.usecase.crypto

import com.sep.byteClub.domain.repository.crypto.KucoinRepository
import javax.inject.Inject

class Ticker24HUseCase @Inject constructor(
    private val kucoinRepository: KucoinRepository
) {

    suspend operator fun invoke(symbol: String) = kucoinRepository.fetch24HTicker(symbol = symbol)

}