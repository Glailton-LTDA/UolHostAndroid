package io.github.glailton.uolhost.core.data.repository

import io.github.glailton.uolhost.core.data.remote.request.AddPlayerRequest
import io.github.glailton.uolhost.core.data.retrofit.ResultWrapper
import io.github.glailton.uolhost.core.domain.models.Player

interface PlayerRepository {
    suspend fun getAllPlayers(): ResultWrapper<List<Player>>

    suspend fun createPlayer(request: AddPlayerRequest): ResultWrapper<Unit>
}