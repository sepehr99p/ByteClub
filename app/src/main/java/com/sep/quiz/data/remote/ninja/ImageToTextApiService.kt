package com.sep.quiz.data.remote.ninja

import com.sep.quiz.data.model.response.imageToText.ImageToTextResponse
import com.sep.quiz.utils.callAdapter.NetworkResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ImageToTextApiService {

    @Headers("X-Api-Key: RTmDg/ButRxwV6UN4nP6Ww==uv2XbjGFmPczlxiZ")
    @GET("v1/imagetotext")
    suspend fun getTextFromImage(@Body image : MultipartBody) : NetworkResponse<ImageToTextResponse>

}