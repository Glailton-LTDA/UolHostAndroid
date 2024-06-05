package io.github.glailton.uolhost.core.data.remote.request

import com.google.gson.annotations.SerializedName
import io.github.glailton.uolhost.core.domain.enums.GroupType

data class AddPlayerRequest(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("groupType") val groupType: GroupType,
)
