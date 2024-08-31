package com.sep.byteClub.utils.callAdapter

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

internal class NetworkResponseCall<T : BaseNetworkResponse>(
    private val delegate: Call<T>,
    private val errorConverter: Converter<ResponseBody, String>,
) : Call<NetworkResponse<T>> {

    override fun enqueue(callback: Callback<NetworkResponse<T>>) {
        return delegate.enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()
                if (response.isSuccessful) {
                    if (code in 200 until 300) {
                        body?.let {
                            if (it.isSuccess) {
                                callback.onResponse(
                                    this@NetworkResponseCall,
                                    Response.success(NetworkResponse.Success(body))
                                )
                            } else {
                                callback.onResponse(
                                    this@NetworkResponseCall,
                                    Response.success(NetworkResponse.ApiError(it.message))
                                )
                            }
                        }
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.ApiError(body?.message.orEmpty())))
                    }
                } else {
                    if (code == 504) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(
                                NetworkResponse.ApiError("Check your internet connection")
                            )
                        )
                    } else {
                        val errorBody = convertToErrorBody(error)
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(
                                NetworkResponse.ApiError(errorBody ?: "Error")
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> NetworkResponse.Exception(throwable)
                    else -> NetworkResponse.Exception(throwable)
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }
        })
    }


    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResponse<T>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun timeout(): Timeout = delegate.timeout()
    override fun request(): Request = delegate.request()


    private fun convertToErrorBody(error: ResponseBody?): String? {

        return when {
            error == null -> null
            error.contentLength() == 0L -> null
            else -> try {
                errorConverter.convert(error)
            } catch (ex: Exception) {
                ex.printStackTrace()
                null
            }
        }
    }

}