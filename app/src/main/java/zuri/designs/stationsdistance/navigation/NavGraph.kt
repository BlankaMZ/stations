package zuri.designs.stationsdistance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import zuri.designs.stationsdistance.screens.HomeScreen
import zuri.designs.stationsdistance.screens.SearchStationScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                navController = navController,
                onSetOriginStationClicked = { navController.navigate(Screen.SearchStation.route) },
                onSetDestinationClicked = { navController.navigate(Screen.SearchStation.route) }
            )
        }
        composable(route = Screen.SearchStation.route) {
            SearchStationScreen(navController = navController)
        }
    }
}
