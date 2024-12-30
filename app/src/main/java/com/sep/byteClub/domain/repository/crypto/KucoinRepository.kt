package com.sep.byteClub.domain.repository.crypto

import com.sep.byteClub.domain.entity.crypto.CandleEntity
import com.sep.byteClub.domain.entity.crypto.CurrencyEntity
import com.sep.byteClub.domain.entity.crypto.SingleTickerEntity
import com.sep.byteClub.domain.entity.crypto.Ticker24hEntity
import com.sep.byteClub.domain.entity.crypto.TickerEntity
import com.sep.byteClub.utils.ResultState


interface KucoinRepository {

    suspend fun fetchCurrencyList() : ResultState<List<CurrencyEntity>>

    suspend fun fetchTicker(symbol : String) : ResultState<SingleTickerEntity>

    suspend fun fetchAllTickers() : ResultState<List<TickerEntity>>

    suspend fun fetchMarketList() : ResultState<List<String>>

    suspend fun fetchCandles(interval : String, symbol: String) : ResultState<List<CandleEntity>>

    suspend fun fetchServerTime() : ResultState<Long>

    suspend fun fetchPrices() : ResultState<String>

    suspend fun fetch24HTicker(symbol : String) : ResultState<Ticker24hEntity>

}