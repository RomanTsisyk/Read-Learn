package slavic_api.github.io.android.ui.views

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import slavic_api.github.io.android.ui.AboutScreen
import slavic_api.github.io.android.ui.LibrariesScreen
import slavic_api.github.io.android.ui.SettingsScreen
import slavic_api.github.io.android.ui.deitiesList.DeitiesListScreen
import slavic_api.github.io.android.ui.deitydetail.DeityDetailScreen

sealed class AppScreen(val route: String) {
    companion object {
        const val MAIN_ROUTE = "main"
        const val DEITY_ID = "deityId"
        const val DETAIL_ROUTE = "detail/{deityId}"
        const val SETTINGS_ROUTE = "settings"
        const val LIBRARIES_ROUTE = "libraries"
        const val ABOUT_ROUTE = "about"
    }

    data object Main : AppScreen(MAIN_ROUTE)
    data object Detail : AppScreen(DETAIL_ROUTE) {
        fun createRoute(deityId: String) = "detail/$deityId"
    }
    data object Settings : AppScreen(SETTINGS_ROUTE)
    data object Libraries : AppScreen(LIBRARIES_ROUTE)
    data object About : AppScreen(ABOUT_ROUTE)
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NavGraph(navController: NavHostController, startDestination: String = AppScreen.Main.route) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(AppScreen.Main.route) {
            DeitiesListScreen { deityId ->
                navController.navigate(AppScreen.Detail.createRoute(deityId))
            }
        }
        composable(
            route = AppScreen.Detail.route,
            arguments = listOf(navArgument(AppScreen.DEITY_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val deityId = backStackEntry.arguments?.getString(AppScreen.DEITY_ID)
            deityId?.let {
                DeityDetailScreen(deityId = it)
            }
        }
        composable(AppScreen.SETTINGS_ROUTE) {
            SettingsScreen()
        }
        composable(AppScreen.LIBRARIES_ROUTE) {
            LibrariesScreen()
        }
        composable(AppScreen.ABOUT_ROUTE) {
            AboutScreen()
        }
    }
}