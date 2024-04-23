package io.github.glailton.uolhost.ui.presentation.screens.add

import io.github.glailton.uolhost.core.domain.models.Player

data class AddState(
    val players: List<Player> = emptyList(),
    val searchedList: List<Player> = emptyList(),
    val isLoading: Boolean = true,
    val searchText: String = "",
    val showNetworkError: Boolean = false,
) {

}
