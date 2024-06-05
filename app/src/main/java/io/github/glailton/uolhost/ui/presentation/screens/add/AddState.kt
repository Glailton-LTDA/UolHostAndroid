package io.github.glailton.uolhost.ui.presentation.screens.add

import io.github.glailton.uolhost.core.domain.enums.GroupType

data class AddState(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val selectedText: GroupType = GroupType.AVENGERS,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val showDialogError: Boolean = false,
    val showSnackBar: Boolean = false,
) {

}
