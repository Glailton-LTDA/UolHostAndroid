package io.github.glailton.uolhost.rule

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.idling.CountingIdlingResource

const val TIME_OUT = 8000L

fun ComposeContentTestRule.waitUntil(timeoutMillis: Long = TIME_OUT, nodeWithText: String, size: Int) {
    waitUntil(timeoutMillis = timeoutMillis) {
        onAllNodesWithText(nodeWithText)
            .fetchSemanticsNodes().size == size
    }
}

fun CountingIdlingResource.register() = IdlingRegistry.getInstance().register(this)

fun CountingIdlingResource.unregister() = IdlingRegistry.getInstance().unregister(this)