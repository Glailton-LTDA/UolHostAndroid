package io.github.glailton.uolhost.core.data.repository

import io.github.glailton.uolhost.core.data.remote.UolApi
import io.github.glailton.uolhost.core.data.remote.response.PlayerResponse
import io.github.glailton.uolhost.core.data.retrofit.Repository
import io.github.glailton.uolhost.core.data.utils.Mapper
import io.github.glailton.uolhost.core.data.utils.mapWith
import io.github.glailton.uolhost.core.domain.models.Player

class PlayerRepositoryImpl(
    private val api: UolApi,
    private val playerMapper: Mapper<PlayerResponse, Player>
): Repository(), PlayerRepository {
    override suspend fun getAllPlayers() =
        executeHttpRequest { api.getAllPlayers().mapWith(playerMapper) }
}