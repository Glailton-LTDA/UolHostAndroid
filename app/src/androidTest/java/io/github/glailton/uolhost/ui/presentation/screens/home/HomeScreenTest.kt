package io.github.glailton.uolhost.ui.presentation.screens.home

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
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

class HomeScreenTest {
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
    fun should_display_players_list() {
        webServerRule.mockPlayerResponse()

        composeTestRule.setContent {
            UolHostTheme {
                HomeScreen(viewModel = getViewModel(), onAddClicked = {})
            }
        }

        composeTestRule.run {
            onNodeWithText(context.getString(R.string.app_name))
                .assertIsDisplayed()

            onNodeWithContentDescription(context.getString(R.string.search_icon))
                .assertIsDisplayed()

            onNodeWithContentDescription(context.getString(R.string.filter_icon))
                .assertIsDisplayed()

            onNodeWithContentDescription(context.getString(R.string.refresh_icon))
                .assertIsDisplayed()

            onAllNodesWithText("test")
                .assertCountEquals(1)
            onAllNodesWithText("Hulk")
                .assertCountEquals(1)
            onAllNodesWithText(context.getString(R.string.avengers))
                .assertCountEquals(1)
        }
    }
}