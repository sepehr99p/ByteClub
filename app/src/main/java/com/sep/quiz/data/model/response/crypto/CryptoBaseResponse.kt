package com.sep.quiz.data.model.response.crypto

import kotlinx.serialization.Serializable

@Serializable
data class CryptoBaseResponse<T>(
    val code : String,
    val data : T
)