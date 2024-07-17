package zuri.designs.stationsdistance.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import zuri.designs.stationsdistance.data.model.StationType

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchStationScreen(
    stationType: StationType,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: SearchStationViewModel = hiltViewModel()
) {
    val focusRequester = remember { FocusRequester() }
    val searchQuery by viewModel.searchQuery.collectAsState()
    val listOfStation = viewModel.stations.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                focusRequester = focusRequester,
                onTextChange = {
                    viewModel.updateSearchQuery(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            ListContent(
                modifier = modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize(),
                items = listOfStation.value,
                onStationClicked = { id ->
                    when (stationType) {
                        StationType.ORIGIN -> navController.previousBackStackEntry?.savedStateHandle?.set(
                            "origin_station",
                            id
                        )
                        StationType.DESTINATION -> navController.previousBackStackEntry?.savedStateHandle?.set(
                            "destination_station",
                            id
                        )
                    }
                    navController.popBackStack()
                }
            )
        }
    )
}
