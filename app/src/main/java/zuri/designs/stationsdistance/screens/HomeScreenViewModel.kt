package zuri.designs.stationsdistance.screens

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import zuri.designs.stationsdistance.data.model.Station
import zuri.designs.stationsdistance.data.model.StationType
import zuri.designs.stationsdistance.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val buttonEnabled by derivedStateOf {
        false
    }

    var origin: Station by mutableStateOf(
        Station(
            id = -1,
            name = "",
            latitude = 0.0,
            longitude = 0.0,
            hits = 0
        )
    )
        private set

    var destination: Station by mutableStateOf(
        Station(
            id = -1,
            name = "",
            latitude = 0.0,
            longitude = 0.0,
            hits = 0
        )
    )
        private set

    suspend fun prepareStations() {
        repository.updateAllStations()
    }

    suspend fun prepareStationKeywords() {
        repository.updateAllStationKeywords()
    }

    suspend fun setChosenStation(type: StationType, id: Int) {
        when (type) {
            StationType.ORIGIN -> origin = repository.getChosenStation(id)
            StationType.DESTINATION -> destination = repository.getChosenStation(id)
        }
    }
}
