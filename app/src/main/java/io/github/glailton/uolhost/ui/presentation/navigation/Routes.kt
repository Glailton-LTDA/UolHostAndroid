package io.github.glailton.uolhost.ui.presentation.navigation

sealed class Routes(val routes: String) {
    data object HomeScreenRoute : Routes("home-screen")
    data object AddScreenRoute : Routes("add-screen")
}