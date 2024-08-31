package com.sep.byteClub.data.remote.ninja

import com.sep.byteClub.data.model.response.imageToText.ImageToTextResponse
import com.sep.byteClub.utils.callAdapter.NetworkResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageToTextApiService {

    @Headers("X-Api-Key: RTmDg/ButRxwV6UN4nP6Ww==uv2XbjGFmPczlxiZ")
    @GET("v1/imagetotext")
    suspend fun getTextFromImage(@Body image : MultipartBody) : NetworkResponse<ImageToTextResponse>

}