package io.github.glailton.uolhost.core.data.retrofit

import androidx.annotation.StringRes

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    object Aborted : ResultWrapper<Nothing>()
    sealed class Error : ResultWrapper<Nothing>() {
        data class Generic(
            val code: Int? = null,
            val error: String? = null,
            @StringRes val messageId: Int? = null
        ) :
            Error()

        object Server : Error()
        object Network : Error()
    }
}