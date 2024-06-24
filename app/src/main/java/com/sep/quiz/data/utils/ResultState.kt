package com.sep.quiz.data.utils

import retrofit2.Response


sealed class ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>()

    data class Failure(val code: String, val error: String) : ResultState<Nothing>()

    data class Exception(val error: Throwable) : ResultState<Nothing>() {
        init {
            error.printStackTrace()
        }
    }

    val isSuccess: Boolean
        get() = this is Success
}


suspend fun <S,T> Response<T>.toResultState(
    onSuccess: suspend (T) -> ResultState.Success<S>
): ResultState<S> {
    return try {
        val result = this
        val code = result.code().toString()
        if (result.isSuccessful) {
            result.body()?.let {
                onSuccess(it)
            } ?: run {
                ResultState.Failure(code, "empty body")
            }
        } else {
            result.errorBody()?.let {
                ResultState.Failure(code, it.string())
            } ?: run {
                ResultState.Failure(code, "empty error body")
            }
        }
    } catch (e: Exception) {
        ResultState.Exception(Throwable(e))
    }
}
