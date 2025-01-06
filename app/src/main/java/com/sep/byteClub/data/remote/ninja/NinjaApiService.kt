package com.sep.byteClub.data.remote.ninja

import com.sep.byteClub.data.model.response.cars.CarDto
import com.sep.byteClub.data.model.response.dadJokes.DadJokeDto
import com.sep.byteClub.data.model.response.dictionary.WordResponse
import com.sep.byteClub.data.model.response.imageToText.ImageToTextDto
import com.sep.byteClub.utils.callAdapter.NetworkResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface NinjaApiService {

    @Headers(tempToken)
    @GET(dadJokeEndpoint)
    suspend fun getJoke(): Response<List<DadJokeDto>>

    @Headers(tempToken)
    @GET(dictionaryEndpoint)
    suspend fun dictionary(@Query("word") word: String): NetworkResponse<WordResponse>

    @Headers(tempToken)
    @Multipart
    @POST(imageToTextEndpoint)
    suspend fun getTextFromImage(@Part file: MultipartBody.Part? = null): Response<List<ImageToTextDto>>


    @Headers(tempToken)
    @GET(carsEndpoint)
    suspend fun getCars(
        @Query("make") manufacture: String?,
        @Query("model") model: String?,
    ): Response<List<CarDto>>

}