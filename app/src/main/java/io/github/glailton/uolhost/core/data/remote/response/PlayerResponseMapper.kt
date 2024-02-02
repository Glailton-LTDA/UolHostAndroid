package io.github.glailton.uolhost.core.data.remote.response

import io.github.glailton.uolhost.core.data.utils.Mapper
import io.github.glailton.uolhost.core.domain.models.Player

class PlayerResponseMapper: Mapper<PlayerResponse, Player> {
    override fun map(from: PlayerResponse) = Player(
        id = from.id,
        name = from.name,
        email = from.email,
        codiname = from.codiname,
        groupType = from.groupType
    )
}