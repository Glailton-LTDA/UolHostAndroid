package io.github.glailton.uolhost.core.data.retrofit.errorResponse

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class ErrorResponse(
    @SerializedName("detail")
    @Json(name = "detail")
    val detail: String,
    @SerializedName("error")
    @Json(name = "error")
    val error: String,
    @SerializedName("type")
    @Json(name = "type")
    val type: String
)