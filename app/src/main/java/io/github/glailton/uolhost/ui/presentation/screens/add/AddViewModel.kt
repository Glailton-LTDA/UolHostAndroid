package io.github.glailton.uolhost.ui.presentation.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.glailton.uolhost.core.data.remote.request.AddPlayerRequest
import io.github.glailton.uolhost.core.data.repository.PlayerRepository
import io.github.glailton.uolhost.core.data.retrofit.ResultWrapper
import io.github.glailton.uolhost.core.domain.enums.GroupType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddViewModel(private val repository: PlayerRepository): ViewModel() {
    private val _state = MutableStateFlow(AddState())
    val state = _state.asStateFlow()


    init {
        hideLoadingState()
    }

    fun updateSelectedText(selectedText: GroupType) {
        _state.update {
            it.copy(selectedText = selectedText)
        }
    }

    fun createPlayer() = viewModelScope.launch {
        handleLoadingState()
        val request = AddPlayerRequest(
            name = _state.value.name,
            email = _state.value.email,
            phoneNumber = _state.value.phone,
            groupType = _state.value.selectedText
        )

        when(repository.createPlayer(request)) {
            is ResultWrapper.Success -> {
                hideLoadingState()
                _state.update {
                    it.copy(showSnackBar = true)
                }
            }

            else -> {
                hideLoadingState()
                _state.update {
                    it.copy(showDialogError = true)
                }
            }
        }
    }

    fun updateIsSuccess(isSuccess: Boolean) {
        _state.update {
            it.copy(isSuccess = isSuccess)
        }
    }

    fun updateName(name: String) {
        _state.update {
            it.copy(name = name)
        }
    }

    fun updateEmail(email: String) {
        _state.update {
            it.copy(email = email)
        }
    }

    fun updatePhoneNumber(phoneNumber: String) {
        _state.update {
            it.copy(phone = phoneNumber)
        }
    }

    fun updateGroupType(groupType: String) {
        _state.update {
            it.copy(selectedText = GroupType.fromName(groupType))
        }
    }

    private fun handleLoadingState() {
        _state.update {
            it.copy(
                isLoading = true
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