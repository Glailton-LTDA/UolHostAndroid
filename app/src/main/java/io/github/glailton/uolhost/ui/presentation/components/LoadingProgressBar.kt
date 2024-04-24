package io.github.glailton.uolhost.ui.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import io.github.glailton.uolhost.ui.presentation.utils.TestTags.LOADING_DIALOG

@Composable
fun LoadingProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag(LOADING_DIALOG),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}