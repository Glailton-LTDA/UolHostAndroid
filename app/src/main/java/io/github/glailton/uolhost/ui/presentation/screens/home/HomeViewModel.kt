package io.github.glailton.uolhost.ui.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.glailton.uolhost.core.data.repository.PlayerRepository
import io.github.glailton.uolhost.core.data.retrofit.ResultWrapper.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PlayerRepository): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun getPlayers() = viewModelScope.launch {
        handleRefreshState()
        when(val result = repository.getAllPlayers()) {
            is Success -> {
                _state.update {
                    it.copy(
                        players = result.value
                    )
                }
            }

            else -> {
                hideRefreshState()
            }
        }
    }

    private fun handleRefreshState() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
    }

    private fun hideRefreshState() {
        _state.update {
            it.copy(
                isLoading = false
            )
        }
    }
}