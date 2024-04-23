package io.github.glailton.uolhost.ui.presentation.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import io.github.glailton.uolhost.MainActivity
import io.github.glailton.uolhost.rule.WebServerRule
import io.github.glailton.uolhost.ui.theme.UolHostTheme
import org.junit.Rule
import org.junit.Test

class EmptyContentTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    @ExperimentalFoundationApi
    private lateinit var scenario: ActivityScenario<MainActivity>

    private val intent = Intent(
        context,
        MainActivity::class.java
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val webServerRule = WebServerRule()

    @Test
    fun should_open_screen() {
        composeTestRule.setContent {
            UolHostTheme {
                EmptyContent()
            }
        }
    }
}