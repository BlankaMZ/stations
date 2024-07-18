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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import zuri.designs.stationsdistance.R
import zuri.designs.stationsdistance.data.model.StationType

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    originStationId: Int = -1,
    destinationStationId: Int = -1,
    onSetOriginStationClicked: () -> Unit = {},
    onSetDestinationClicked: () -> Unit = {},
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        launch(Dispatchers.IO) {
            viewModel.prepareStations()
            viewModel.prepareStationKeywords()
        }
        if (originStationId != -1) viewModel.setChosenStation(StationType.ORIGIN, originStationId)
        if (destinationStationId != -1) {
            viewModel.setChosenStation(
                StationType.DESTINATION,
                destinationStationId
            )
        }
    }

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
                    label = stringResource(id = R.string.station_of_origin),
                    name = viewModel.origin.name,
                    onPlaceFieldClicked = {
                        onSetOriginStationClicked()
                        viewModel.resetTheDistance()
                    }
                )
                TextWithField(
                    label = stringResource(id = R.string.station_of_destination),
                    name = viewModel.destination.name,
                    onPlaceFieldClicked = {
                        onSetDestinationClicked()
                        viewModel.resetTheDistance()
                    }
                )
                Button(
                    onClick = {
                        viewModel.calculateTheDistance()
                    },
                    enabled = viewModel.buttonEnabled,
                    modifier = Modifier
                        .widthIn(240.dp)
                        .padding(vertical = 16.dp)
                        .padding(top = 8.dp)
                ) {
                    Text(stringResource(id = R.string.common_calculate))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            stringResource(id = R.string.approximate_distance),
                            textAlign = TextAlign.Center
                        )
                        if (viewModel.distanceBetweenStations != -1.0) {
                            Text(
                                "${viewModel.distanceBetweenStations} ${stringResource(id = R.string.common_km)}",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TextWithField(
    label: String,
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
            label,
            modifier = Modifier.weight(1f)
        )
        TextField(
            value = name,
            onValueChange = {},
            modifier
                .weight(3f)
                .clickable { onPlaceFieldClicked() },
            singleLine = true,
            enabled = false
        )
    }
}
