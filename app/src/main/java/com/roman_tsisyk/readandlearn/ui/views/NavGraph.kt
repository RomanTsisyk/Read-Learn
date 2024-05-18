package com.roman_tsisyk.readandlearn.ui.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.roman_tsisyk.readandlearn.ui.deitiesList.DeitiesListScreen
import com.roman_tsisyk.readandlearn.ui.deitydetail.DeityDetailScreen

sealed class AppScreen(val route: String) {
    data object Main : AppScreen("main")
    data object Detail : AppScreen("detail/{deityId}") {
        fun createRoute(deityId: String) = "detail/$deityId"
    }
}

@Composable
fun NavGraph(startDestination: String = AppScreen.Main.route) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(AppScreen.Main.route) {
            DeitiesListScreen { deityId ->
                navController.navigate(AppScreen.Detail.createRoute(deityId))
            }
        }
        composable(
            route = AppScreen.Detail.route,
            arguments = listOf(navArgument("deityId") { type = NavType.StringType })
        ) { backStackEntry ->
            val deityId = backStackEntry.arguments?.getString("deityId")
            deityId?.let {
                DeityDetailScreen(deityId = it)
            }
        }
    }
}