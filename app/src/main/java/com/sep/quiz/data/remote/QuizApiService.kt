package com.sep.quiz.data.remote

import com.sep.quiz.data.model.response.CategoryResponse
import com.sep.quiz.data.model.response.QuestionCountResponse
import com.sep.quiz.data.model.response.QuestionResponse
import com.sep.quiz.data.model.response.ResetTokenResponse
import com.sep.quiz.data.model.response.RetrieveTokenResponse
import com.sep.quiz.utils.callAdapter.NetworkResponse
import retrofit2.Response
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