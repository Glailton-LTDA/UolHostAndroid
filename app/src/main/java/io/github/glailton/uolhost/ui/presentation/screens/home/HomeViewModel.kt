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
        handleLoadingState()
        when(val result = repository.getAllPlayers()) {
            is Success -> {
                hideLoadingState()
                _state.update {
                    it.playersWith(result.value)
                }
            }

            else -> {
                hideLoadingState()
                handleNetworkErrorState()
            }
        }
    }

    private fun handleLoadingState() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
    }

    private fun handleNetworkErrorState() {
        _state.update {
            it.copy(
                showNetworkError = true
            )
        }
    }

    private fun hideLoadingState() {
        _state.update {
            it.copy(
                isLoading = false
            )
        }
    }
}