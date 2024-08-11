package com.sep.quiz.data.remote.crypto

import com.sep.quiz.data.model.response.crypto.AccountResponse
import com.sep.quiz.data.model.response.crypto.AccountSummeryResponse
import com.sep.quiz.data.model.response.crypto.AllTickersResponse
import com.sep.quiz.data.model.response.crypto.CryptoBaseResponse
import com.sep.quiz.data.model.response.crypto.CurrencyResponse
import com.sep.quiz.data.model.response.crypto.PriceResponse
import com.sep.quiz.data.model.response.crypto.SingleTickerResponse
import com.sep.quiz.data.model.response.crypto.Ticker24hResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KucoinApiService {

    @GET(currencyList)
    suspend fun fetchCurrencyList(): CryptoBaseResponse<List<CurrencyResponse>>

    @GET(fetchTicker)
    suspend fun fetchTicker(@Query("symbol") symbol: String): CryptoBaseResponse<SingleTickerResponse>

    @GET(fetch24h)
    suspend fun fetch24h(@Query("symbol") symbol: String): CryptoBaseResponse<Ticker24hResponse>

    @GET(fetchAllTickers)
    suspend fun fetchAllTickers(): CryptoBaseResponse<AllTickersResponse>

    @GET(marketList)
    suspend fun fetchMarketList(): CryptoBaseResponse<List<String>>

    @GET(candles)
    suspend fun fetchCandles(
        @Query("type") type: String,
        @Query("symbol") symbol: String
    ): CryptoBaseResponse<List<List<String>>>

    @GET(serverTime)
    suspend fun serverTime(): CryptoBaseResponse<Long>

    @GET(fetchPrices)
    suspend fun getPrices(): CryptoBaseResponse<PriceResponse>

    @GET(accountSummary)
    suspend fun getAccountSummery(): Response<AccountSummeryResponse>

    @GET(accounts)
    suspend fun fetchAccount(): Response<List<AccountResponse>>

}