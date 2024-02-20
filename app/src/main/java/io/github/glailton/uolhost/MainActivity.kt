package io.github.glailton.uolhost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import io.github.glailton.uolhost.ui.presentation.navigation.UolHostNavigation
import io.github.glailton.uolhost.ui.theme.UolHostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UolHostTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    UolHostNavigation()
                }
            }
        }
    }
}