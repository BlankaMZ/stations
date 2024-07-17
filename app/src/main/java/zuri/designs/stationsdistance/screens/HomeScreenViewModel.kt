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
import kotlin.math.round

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val buttonEnabled by derivedStateOf {
        origin.id != -1 && destination.id != -1
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

    var distanceBetweenStations by mutableStateOf(-1.0)
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

    // using haversine calculation for an approximate straight line distance that will work offline
    fun calculateTheDistance() {
        val earthRadiusInKm = 6372.8
        val dLat = Math.toRadians(destination.latitude - origin.latitude)
        val dLon = Math.toRadians(destination.longitude - origin.longitude)
        val originLat = Math.toRadians(origin.latitude)
        val destinationLat = Math.toRadians(destination.latitude)

        val a = Math.pow(Math.sin(dLat / 2), 2.toDouble()) + Math.pow(
            Math.sin(dLon / 2),
            2.toDouble()
        ) * Math.cos(originLat) * Math.cos(destinationLat)
        val c = 2 * Math.asin(Math.sqrt(a))
        val result = earthRadiusInKm * c
        val roundedResult = (round(result * 100)) / 100
        distanceBetweenStations = roundedResult
    }
}
