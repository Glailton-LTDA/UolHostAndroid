package io.github.glailton.uolhost.core.di

import io.github.glailton.uolhost.core.data.remote.UolApi
import io.github.glailton.uolhost.core.utils.ApiEndpoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://10.0.2.2:5242/uol/"

fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        addLoggingInterceptor()
    }.build()
}

private fun OkHttpClient.Builder.addLoggingInterceptor() {
    val loggingInterceptor = HttpLoggingInterceptor()

    if (BuildConfig.DEBUG) {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    }

    addInterceptor(loggingInterceptor)
}

fun provideRetrofit(apiEndpoint: ApiEndpoint, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl(apiEndpoint.baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideUolApi(retrofit: Retrofit): UolApi {
    return retrofit.create(UolApi::class.java)
}