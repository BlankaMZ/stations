package zuri.designs.stationsdistance.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchStationScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: SearchStationViewModel = hiltViewModel()
) {
    val searchQuery by viewModel.searchQuery
    val list = viewModel.keywords.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChange = {
                    viewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    viewModel.searchStationKeywords(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            ListContent(
                modifier = modifier.padding(top = it.calculateTopPadding()).fillMaxSize(),
                items = list.value
            )
        }
    )
}
