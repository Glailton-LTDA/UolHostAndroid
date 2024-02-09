package io.github.glailton.uolhost.ui.presentation.screens.home

import io.github.glailton.uolhost.core.domain.models.Player

data class HomeState(
    val players: List<Player> = emptyList(),
    val searchedList: List<Player> = emptyList(),
    val isLoading: Boolean = true,
    val searchText: String = "",
    val showNetworkError: Boolean = false,
) {
    fun playersWith(players: List<Player> ): HomeState {
        return copy(
            players = players,
            isLoading = false,
            showNetworkError = false
        )
    }
}
