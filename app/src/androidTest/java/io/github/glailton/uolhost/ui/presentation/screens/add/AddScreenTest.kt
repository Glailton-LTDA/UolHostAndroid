package io.github.glailton.uolhost.ui.presentation.screens.add

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import io.github.glailton.uolhost.MainActivity
import io.github.glailton.uolhost.R
import io.github.glailton.uolhost.rule.WebServerRule
import io.github.glailton.uolhost.rule.mockPlayerResponse
import io.github.glailton.uolhost.ui.theme.UolHostTheme
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.compose.getViewModel

class AddScreenTest {
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
    fun should_add_screen_elements() {
        webServerRule.mockPlayerResponse()

        composeTestRule.setContent {
            UolHostTheme {
                AddScreen(viewModel = getViewModel(), onBackClicked = {}, onDismiss = {(true)})
            }
        }

        composeTestRule.run {
            onNodeWithText(context.getString(R.string.app_name))
                .assertIsDisplayed()

            onNodeWithText(context.getString(R.string.add_player))
                .assertIsDisplayed()

            onNodeWithText(context.getString(R.string.name))
                .assertIsDisplayed()

            onNodeWithText(context.getString(R.string.email))
                .assertIsDisplayed()

            onNodeWithText(context.getString(R.string.phone_number))
                .assertIsDisplayed()

            onNodeWithText(context.getString(R.string.create))
                .assertIsDisplayed()
        }
    }
}