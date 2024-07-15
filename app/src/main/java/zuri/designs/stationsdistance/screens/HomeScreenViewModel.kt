package zuri.designs.stationsdistance.screens

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import zuri.designs.stationsdistance.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val buttonEnabled by derivedStateOf {
        false
    }

    suspend fun prepareStations() {
        repository.updateAllStations()
    }

    suspend fun prepareStationKeywords() {
        repository.updateAllStationKeywords()
    }
}
