package zuri.designs.stationsdistance.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import zuri.designs.stationsdistance.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class SearchStationViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedStations = MutableStateFlow("")
    val searchedStations = _searchedStations

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchStations(query: String) {
    }
}
