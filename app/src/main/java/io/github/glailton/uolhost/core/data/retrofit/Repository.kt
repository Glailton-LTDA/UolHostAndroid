package io.github.glailton.uolhost.core.data.retrofit

import io.github.glailton.uolhost.core.data.retrofit.errorHandler.ApiErrorHandler
import io.github.glailton.uolhost.core.data.retrofit.errorHandler.ApiErrorHandlerImpl

abstract class Repository(private val apiErrorHandler: ApiErrorHandler = ApiErrorHandlerImpl()) :
    HttpRepository {

    override suspend fun <T> executeHttpRequest(
        apiCall: suspend () -> T
    ): ResultWrapper<T> = try {
        ResultWrapper.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        apiErrorHandler.errorResultFrom(throwable)
    }
}