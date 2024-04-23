package io.github.glailton.uolhost.ui.presentation.screens.add

import androidx.lifecycle.ViewModel
import io.github.glailton.uolhost.core.data.repository.PlayerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddViewModel(private val repository: PlayerRepository): ViewModel() {
    private val _state = MutableStateFlow(AddState())
    val state = _state.asStateFlow()

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