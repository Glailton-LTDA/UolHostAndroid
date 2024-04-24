package io.github.glailton.uolhost.ui.presentation.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import io.github.glailton.uolhost.MainActivity
import io.github.glailton.uolhost.R
import io.github.glailton.uolhost.rule.WebServerRule
import io.github.glailton.uolhost.ui.theme.UolHostTheme
import org.junit.Rule
import org.junit.Test

class GradientButtonTest {
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
    fun should_display_gradient_button() {
        composeTestRule.setContent {
            UolHostTheme {
                GradientButton(
                    gradientColors = listOf(Color(0xFFff669f), Color(0xFFff8961)),
                    cornerRadius = 16.dp,
                    nameButton = stringResource(id = R.string.try_again_label),
                    roundedCornerShape = RoundedCornerShape(topStart = 30.dp,bottomEnd = 30.dp),
                    onClick = {  }
                )
            }
        }

        composeTestRule.run {

            onNodeWithText(context.getString(R.string.try_again_label))
                .assertIsDisplayed()
        }

    }
}