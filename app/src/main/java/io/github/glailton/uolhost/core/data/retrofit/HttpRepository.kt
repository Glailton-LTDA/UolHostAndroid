package io.github.glailton.uolhost.core.data.retrofit

interface HttpRepository {
    suspend fun <T> executeHttpRequest(
        apiCall: suspend () -> T
    ): ResultWrapper<T>
}