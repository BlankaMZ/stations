package zuri.designs.stationsdistance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import zuri.designs.stationsdistance.data.model.StationType
import zuri.designs.stationsdistance.screens.HomeScreen
import zuri.designs.stationsdistance.screens.SearchStationScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) { entry ->
            val origin = entry.savedStateHandle.get<Int>("origin_station")
            val destination = entry.savedStateHandle.get<Int>("destination_station")
            HomeScreen(
                navController = navController,
                originStationId = origin ?: -1,
                destinationStationId = destination ?: -1,
                onSetOriginStationClicked = { navController.navigate(Screen.SearchOriginStation.route) },
                onSetDestinationClicked = { navController.navigate(Screen.SearchDestinationStation.route) }
            )
        }
        composable(route = Screen.SearchOriginStation.route) {
            SearchStationScreen(
                navController = navController,
                stationType = StationType.ORIGIN
            )
        }
        composable(route = Screen.SearchDestinationStation.route) {
            SearchStationScreen(
                navController = navController,
                stationType = StationType.DESTINATION
            )
        }
    }
}
