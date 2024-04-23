package io.github.glailton.uolhost.ui.presentation.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.glailton.uolhost.ui.presentation.components.TopBar

@Composable
fun AddScreen(
    viewModel: AddViewModel,
    onBackClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                showBackIcon = true,
                onBack = { onBackClicked() }
            )
        },
        content = {
            Column(modifier = Modifier.padding(it)) {

            }
        }
    )
}