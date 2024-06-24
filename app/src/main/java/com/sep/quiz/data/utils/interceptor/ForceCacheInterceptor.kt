package com.sep.quiz.data.utils.interceptor

import com.sep.quiz.data.utils.NetworkConnection
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

internal class ForceCacheInterceptor(private val networkConnection: NetworkConnection) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token =
            "sk-org-s36cjlapgknripddhstywrxk-eipsmyN0QncttBPKfthqT3BlbkFJil7I5ZakYyMJ6MnlUxVE"
        val builder: Request.Builder = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
//            .addHeader("OpenAI-Organization", "org-s36CJLaPgKnrIPdDhSTyWrxk")
//            .addHeader("OpenAI-Project", "Android-Chat")
        if (!networkConnection.isInternetOn()) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }
        return chain.proceed(builder.build())
    }
}