package hu.bme.aut.android.mymangaapp.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import hu.bme.aut.android.mymangaapp.ui.Mangas
import hu.bme.aut.android.mymangaapp.ui.details.MangaDetails

@Composable
fun MangaMainScreen() {
    val navController = rememberNavController()

    ProvideWindowInsets {
        NavHost(navController = navController, startDestination = NavScreen.Home.route) {
            composable(NavScreen.Home.route) {
                Mangas(
                    viewModel = hiltViewModel(),
                    selectManga = {
                        navController.navigate("${NavScreen.MangaDetails.route}/$it")
                    }
                )
            }
            composable(
                route = NavScreen.MangaDetails.routeWithArgument,
                arguments = listOf(
                    navArgument(NavScreen.MangaDetails.argument0) { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val posterId =
                    backStackEntry.arguments?.getString(NavScreen.MangaDetails.argument0) ?: return@composable
                MangaDetails(posterId = posterId, viewModel = hiltViewModel()) {
                    navController.navigateUp()
                }
            }
        }
    }
}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object MangaDetails : NavScreen("MangaDetails") {

        const val routeWithArgument: String = "MangaDetails/{mangaId}"

        const val argument0: String = "mangaId"
    }
}