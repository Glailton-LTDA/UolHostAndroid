package io.github.glailton.uolhost.core.data.remote

import io.github.glailton.uolhost.core.data.remote.response.PlayerResponse
import retrofit2.http.GET

interface UolApi {
    @GET("/players")
    suspend fun getAllPlayers(): List<PlayerResponse>
}