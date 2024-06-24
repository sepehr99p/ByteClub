package com.sep.quiz.data.utils.callAdapter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
sealed class NetworkResponse<out T : BaseNetworkResponse> {
    @Serializable
    data class Success<out T : BaseNetworkResponse>(val data: T?) : NetworkResponse<T>()

    @Serializable
    data class ApiError(val error: ServerError) : NetworkResponse<Nothing>()
    data class Exception(val throwable: Throwable) : NetworkResponse<Nothing>()
}

@Serializable
data class ServerError(
    @SerialName("error")
    val error: String? = "",
    @SerialName("path")
    val path: String? = "",
    @SerialName("status")
    val status: Int? = 0,
    @SerialName("timestamp")
    val timestamp: Long? = 0L
)


@Serializable
open class BaseNetworkResponse(
) {

}
