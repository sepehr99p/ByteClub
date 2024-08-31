package com.sep.byteClub.domain.usecase.crypto

import com.sep.byteClub.domain.repository.crypto.KucoinRepository
import com.sep.byteClub.utils.ResultState
import javax.inject.Inject

class FetchPriceUseCase @Inject constructor(
    private val kucoinRepository: KucoinRepository
) {
    suspend operator fun invoke(): ResultState<String> = kucoinRepository.fetchPrices()
}