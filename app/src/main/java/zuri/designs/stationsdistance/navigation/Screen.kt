package zuri.designs.stationsdistance.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object SearchOriginStation : Screen("search_origin_station_screen")
    object SearchDestinationStation : Screen("search_destination_station_screen")
}
