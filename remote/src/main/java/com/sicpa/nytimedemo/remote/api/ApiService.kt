package com.sicpa.nytimedemo.remote.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {
    private const val OK_HTTP_TIMEOUT = 30L

    fun create(
        isDebug: Boolean,
        baseUrl: String,
        apiKey: String
    ): ArticleService {
        val retrofit = createRetrofit(isDebug, baseUrl, apiKey)
        return retrofit.create(ArticleService::class.java)
    }

    private fun createRetrofit(
        isDebug: Boolean,
        baseUrl: String,
        apiKey: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                createOkHttpClient(
                    createLoggingInterceptor(isDebug),
                    apiKey
                )
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiKey: String
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api-key", apiKey).build()
                request.url(url)
                return@addInterceptor chain.proceed(request.build())
            }
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}
