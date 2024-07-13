package zuri.designs.stationsdistance.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object SearchStation : Screen("search_station_screen")
}
