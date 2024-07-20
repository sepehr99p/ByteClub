package com.sep.quiz.data.remote.crypto

import com.sep.quiz.data.model.response.crypto.AccountResponse
import com.sep.quiz.data.model.response.crypto.AccountSummeryResponse
import com.sep.quiz.data.model.response.crypto.AllTickersResponse
import com.sep.quiz.data.model.response.crypto.CurrencyResponse
import com.sep.quiz.data.model.response.crypto.PriceResponse
import com.sep.quiz.data.model.response.crypto.SingleTickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KucoinApiService {

    @GET(currencyList)
    suspend fun fetchCurrencyList(): Response<List<CurrencyResponse>>

    @GET(fetchTicker)
    suspend fun fetchTicker(@Query("symbol") symbol: String): Response<SingleTickerResponse>

    @GET(fetchAllTickers)
    suspend fun fetchAllTickers(): Response<AllTickersResponse>

    @GET(marketList)
    suspend fun fetchMarketList(): Response<List<String>>

    @GET(candles)
    suspend fun fetchCandles(
        @Query("type") type: String,
        @Query("symbol") symbol: String
    ): Response<List<List<String>>>

    @GET(serverTime)
    suspend fun serverTime(): Response<Long>

    @GET(fetchPrices)
    suspend fun getPrices(): Response<PriceResponse>

    @GET(accountSummary)
    suspend fun getAccountSummery() : Response<AccountSummeryResponse>

    @GET(accounts)
    suspend fun fetchAccount() : Response<List<AccountResponse>>

}