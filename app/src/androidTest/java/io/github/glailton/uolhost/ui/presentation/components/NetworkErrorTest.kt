package io.github.glailton.uolhost.ui.presentation.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import io.github.glailton.uolhost.MainActivity
import io.github.glailton.uolhost.R
import io.github.glailton.uolhost.rule.WebServerRule
import io.github.glailton.uolhost.ui.theme.UolHostTheme
import org.junit.Rule
import org.junit.Test

class NetworkErrorTest {
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
    fun should_display_network_error_screen() {
        composeTestRule.setContent {
            UolHostTheme {
                NetworkError(onTryAgain = {})
            }
        }

        composeTestRule.run {
            onNodeWithContentDescription(context.getString(R.string.network_error_title))
                .assertIsDisplayed()

            onNodeWithText(context.getString(R.string.network_error_title))
                .assertIsDisplayed()

            onNodeWithText(context.getString(R.string.network_error_message))
                .assertIsDisplayed()
        }

    }
}