package io.github.glailton.uolhost.core.data.retrofit.errorHandler

import io.github.glailton.uolhost.core.data.retrofit.ResultWrapper

interface ApiErrorHandler {
    fun <T> errorResultFrom(throwable: Throwable): ResultWrapper<T>
}