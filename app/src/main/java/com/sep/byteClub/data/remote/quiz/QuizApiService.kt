package com.sep.byteClub.data.remote.quiz

import com.sep.byteClub.data.model.response.quiz.CategoryResponse
import com.sep.byteClub.data.model.response.quiz.QuestionCountResponse
import com.sep.byteClub.data.model.response.quiz.QuestionResponse
import com.sep.byteClub.data.model.response.quiz.ResetTokenResponse
import com.sep.byteClub.data.model.response.quiz.RetrieveTokenResponse
import com.sep.byteClub.utils.callAdapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApiService {

    @GET("api_token.php?command=request")
    suspend fun retrieveToken(): NetworkResponse<RetrieveTokenResponse>

    @GET("api_token.php?command=reset")
    suspend fun resetToken(
        @Query("token") token: String
    ): NetworkResponse<ResetTokenResponse>

    //todo : add token here later
    @GET("api.php")
    suspend fun inquiry(
        @Query("amount") amount: Int ,// = 10,
        @Query("category") category: String,
        @Query("difficulty") difficulty : String, // = "easy",
//        @Query("type") type : String , //= "multiple" //multiple & boolean
    ) : NetworkResponse<QuestionResponse>

    @GET("api_category.php")
    suspend fun fetchCategory(): NetworkResponse<CategoryResponse>

    @GET("api_count.php")
    suspend fun fetchQuestionsCount(
        @Query("category") category: String
    ) : NetworkResponse<QuestionCountResponse>

}