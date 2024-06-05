package io.github.glailton.uolhost.rule

import io.github.glailton.uolhost.core.data.remote.response.PlayerResponse
import io.github.glailton.uolhost.core.domain.enums.GroupType

fun WebServerRule.mockPlayerResponse() {
    enqueueResponse(
        listOf(
            PlayerResponse(1, "test", "test@email.com", "Hulk", GroupType.AVENGERS)
        )
    )
}