package zuri.designs.stationsdistance.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import zuri.designs.stationsdistance.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSetOriginStationClicked: () -> Unit = {},
    onSetDestinationClicked: () -> Unit = {},
    onSearchButtonClicked: () -> Unit = {},
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavHostController,
) {

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(id = R.string.main_title))}) },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 16.dp)
                .fillMaxSize()
        ) {
            Row {
                Text(
                    stringResource(id = R.string.station_of_origin),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                TextField(value = "", onValueChange = {}, modifier.clickable { onSetOriginStationClicked() }, singleLine = true)
            }

            Row {
                Text(
                    stringResource(id = R.string.station_of_destination),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                TextField(value = "", onValueChange = {}, modifier.clickable { onSetOriginStationClicked() }, singleLine = true)
            }
            Button(
                onClick = { },
                enabled = viewModel.buttonEnabled,
                modifier = Modifier
                    .widthIn(160.dp)
                    .padding(vertical = 16.dp)
            ) {
                Text(stringResource(id = R.string.common_search ))
            }
        }
    }
}