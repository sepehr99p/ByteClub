package com.sep.byteClub.data.remote.ninja

import com.sep.byteClub.data.model.response.imageToText.ImageToTextResponse
import com.sep.byteClub.utils.callAdapter.NetworkResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageToTextApiService {

    @Headers("X-Api-Key: RTmDg/ButRxwV6UN4nP6Ww==uv2XbjGFmPczlxiZ")
    @Multipart
    @POST("v1/imagetotext")
    suspend fun getTextFromImage(@Part file: MultipartBody.Part? = null): NetworkResponse<ImageToTextResponse>

}