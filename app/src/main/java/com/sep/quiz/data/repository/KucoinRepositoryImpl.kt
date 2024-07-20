package com.sep.quiz.data.repository

import com.sep.quiz.data.remote.crypto.KucoinApiService
import com.sep.quiz.domain.entiry.crypto.CandleEntity
import com.sep.quiz.domain.entiry.crypto.CurrencyEntity
import com.sep.quiz.domain.entiry.crypto.SingleTickerEntity
import com.sep.quiz.domain.entiry.crypto.TickerEntity
import com.sep.quiz.domain.repository.KucoinRepository
import com.sep.quiz.utils.ResultState
import javax.inject.Inject

class KucoinRepositoryImpl @Inject constructor(
    private val kucoinApiService: KucoinApiService
) : KucoinRepository {
    override suspend fun fetchCurrencyList(): ResultState<List<CurrencyEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTicker(symbol: String): ResultState<SingleTickerEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchAllTickers(): ResultState<List<TickerEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchMarketList(): ResultState<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchCandles(
        interval: String,
        symbol: String
    ): ResultState<List<CandleEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchServerTime(): ResultState<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchPrices(): ResultState<String> {
        TODO("Not yet implemented")
    }
}