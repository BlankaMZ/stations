package zuri.designs.stationsdistance.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import zuri.designs.stationsdistance.data.model.StationKeyword
import zuri.designs.stationsdistance.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class SearchStationViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchedStationKeywords = mutableStateListOf<StationKeyword>()
    val searchedStationKeywords = _searchedStationKeywords

    private val _keywords = repository.keywords
    val keywords = _keywords

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchStationKeywords(query: String) {
        viewModelScope.launch {
            // TODO
        }
    }
}
