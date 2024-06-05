package io.github.glailton.uolhost.core.data.remote

import io.github.glailton.uolhost.core.data.remote.request.AddPlayerRequest
import io.github.glailton.uolhost.core.data.remote.response.PlayerResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UolApi {
    @GET("/players")
    suspend fun getAllPlayers(): List<PlayerResponse>

    @POST("/players")
    suspend fun createPlayer(@Body request: AddPlayerRequest)
}