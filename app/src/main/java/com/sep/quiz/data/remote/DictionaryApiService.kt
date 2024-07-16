package com.sep.quiz.data.remote

import com.sep.quiz.data.model.response.dictionary.WordResponse
import com.sep.quiz.utils.callAdapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DictionaryApiService {

    @Headers("X-Api-Key: RTmDg/ButRxwV6UN4nP6Ww==uv2XbjGFmPczlxiZ")
    @GET("v1/dictionary")
    suspend fun dictionary(@Query("word") word : String) : NetworkResponse<WordResponse>

}