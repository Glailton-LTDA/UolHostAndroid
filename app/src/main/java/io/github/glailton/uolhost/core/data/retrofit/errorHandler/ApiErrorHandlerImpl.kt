package io.github.glailton.uolhost.core.data.retrofit.errorHandler

import android.util.Log
import com.google.gson.Gson
import io.github.glailton.uolhost.R
import io.github.glailton.uolhost.core.data.retrofit.ResultWrapper
import io.github.glailton.uolhost.core.data.retrofit.badRequestResponse.BadRequestResponse
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ApiErrorHandlerImpl : ApiErrorHandler {

    override fun <T> errorResultFrom(throwable: Throwable): ResultWrapper<T> = when (throwable) {
        is IOException -> ResultWrapper.Error.Network
        is HttpException -> when (throwable.code()) {
            401 -> ResultWrapper.Error.Generic(
                code = 401,
                messageId = R.string.error_not_authorized
            )

            404 -> ResultWrapper.Error.Network
            in 500 until 600 -> ResultWrapper.Error.Server
            else -> httpExceptionToGenericError(throwable) ?: ResultWrapper.Error.Network
        }

        else -> throw throwable
    }

    private fun httpExceptionToGenericError(httpException: HttpException): ResultWrapper.Error.Generic? {
        val errorMessage = extractErrorFromResponse(httpException.response())

        return if (!errorMessage.isNullOrBlank()) ResultWrapper.Error.Generic(
            httpException.code(),
            errorMessage
        )
        else null
    }

    private fun <T> extractErrorFromResponse(response: Response<T>?): String? {
        val errorBody = response?.errorBody() ?: return null
        return try {
            val jsonTokener = JSONTokener(errorBody.string())
            when (val result = jsonTokener.nextValue()) {
                is String -> result
                is JSONArray -> result.getString(0)
                is JSONObject -> extractErrorMessages(result)
                else -> result.toString()
            }
        } catch (e: JSONException) {
            errorBody.string()
        }
    }

    private fun extractErrorMessages(error: JSONObject): String {
        return try {
            extractNewErrorFormat(error)
        } catch (e: Exception) {
            error.getString("message")
        }
    }

    private fun extractNewErrorFormat(jsonObject: JSONObject): String {
        val badRequestResponse =
            Gson().fromJson(jsonObject.toString(), BadRequestResponse::class.java)
        Log.e("BadRequestResponse", "HTTP error trace ID: ${badRequestResponse.traceId}")
        return badRequestResponse.errors.joinToString(separator = "\n") { it.detail }
    }
}