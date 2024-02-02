package io.github.glailton.uolhost.core.domain.models

import io.github.glailton.uolhost.core.domain.enums.GroupType

data class Player(
    val id: Long,
    val name: String,
    val email: String,
    val codiname: String,
    val groupType: GroupType
)