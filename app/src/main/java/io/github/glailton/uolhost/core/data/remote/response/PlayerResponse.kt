package io.github.glailton.uolhost.core.data.remote.response

import io.github.glailton.uolhost.core.domain.enums.GroupType

data class PlayerResponse(
    val id: Long,
    val name: String,
    val email: String,
    val codiname: String,
    val groupType: GroupType
)
