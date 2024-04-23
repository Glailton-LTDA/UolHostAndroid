package io.github.glailton.uolhost.ui.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.glailton.uolhost.ui.presentation.screens.add.AddScreen
import io.github.glailton.uolhost.ui.presentation.screens.add.AddViewModel
import io.github.glailton.uolhost.ui.presentation.screens.home.HomeScreen
import io.github.glailton.uolhost.ui.presentation.screens.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UolHostNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Routes.HomeScreenRoute.routes) {
        navHomeScreen(navController)
        navAddScreen(navController)
    }
}

fun NavGraphBuilder.navHomeScreen(navController: NavHostController) {
    composable(route = Routes.HomeScreenRoute.routes) {
        val viewModel: HomeViewModel = koinViewModel()
        HomeScreen(
            viewModel = viewModel,
            onAddClicked = { navController.navigate(Routes.AddScreenRoute.routes) })
    }
}

fun NavGraphBuilder.navAddScreen(navController: NavHostController) {
    composable(route = Routes.AddScreenRoute.routes) {
        val viewModel: AddViewModel = koinViewModel()
        BackHandler {
            navController.popBackStack()
        }
        AddScreen(
            viewModel = viewModel,
            onBackClicked = { navController.popBackStack() }
        )
    }
}