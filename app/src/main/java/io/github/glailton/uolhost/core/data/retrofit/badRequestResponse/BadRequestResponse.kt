package io.github.glailton.uolhost.core.data.retrofit.badRequestResponse

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import io.github.glailton.uolhost.core.data.retrofit.errorResponse.ErrorResponse

data class BadRequestResponse(
    @SerializedName("errors")
    @Json(name = "errors")
    val errors: List<ErrorResponse>,
    @SerializedName("instance")
    @Json(name = "instance")
    val instance: String,
    @SerializedName("traceId")
    @Json(name = "traceId")
    val traceId: String
)