package com.sep.quiz.utils.callAdapter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
sealed class NetworkResponse<out T : BaseNetworkResponse> {
    @Serializable
    data class Success<out T : BaseNetworkResponse>(val data: T?) : NetworkResponse<T>()

    @Serializable
    data class ApiError(val error: String) : NetworkResponse<Nothing>()
    data class Exception(val throwable: Throwable) : NetworkResponse<Nothing>()
}



@Serializable
open class BaseNetworkResponse(
    @SerialName("response_code")
    val code : Int = 0,
    @SerialName("response_message")
    val message : String = ""
) {
    val isSuccess: Boolean
        get() = code == 0

    val hasTokenError: Boolean = (code == 3) || (code == 4)
}
