package zuri.designs.stationsdistance.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    navController: NavHostController
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(id = R.string.main_title)) }) },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(top = 16.dp)
                    .fillMaxSize()
            ) {
                TextWithField(
                    name = stringResource(id = R.string.station_of_origin),
                    onPlaceFieldClicked = { onSetOriginStationClicked() }
                )
                TextWithField(
                    name = stringResource(id = R.string.station_of_destination),
                    onPlaceFieldClicked = { onSetDestinationClicked() }
                )
                Button(
                    onClick = { onSearchButtonClicked() },
                    enabled = viewModel.buttonEnabled,
                    modifier = Modifier
                        .widthIn(240.dp)
                        .padding(vertical = 16.dp)
                        .padding(top = 8.dp)
                ) {
                    Text(stringResource(id = R.string.common_search))
                }
            }
        }
    }
}

@Composable
fun TextWithField(
    name: String,
    modifier: Modifier = Modifier,
    onPlaceFieldClicked: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            name,
            modifier = Modifier.weight(1f)
        )
        TextField(
            value = "",
            onValueChange = {},
            modifier
                .weight(3f)
                .clickable { onPlaceFieldClicked() },
            singleLine = true,
            enabled = false
        )
    }
}
