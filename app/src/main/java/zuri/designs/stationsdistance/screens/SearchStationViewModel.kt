package zuri.designs.stationsdistance.screens

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import zuri.designs.stationsdistance.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class SearchStationViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _keywords = repository.keywords

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    val stations = searchQuery
        .debounce(200L)
        .onEach { _isSearching.update { true } }
        .combine(_keywords) { text, keywords ->
            if (text.isBlank()) {
                emptyList()
            } else {
                repository.getSearchedStations(
                    keywords.filter { keyword ->
                        keyword.doesMatchSearchQuery(text)
                    }.map { it.stationId }
                )
            }
        }.onEach {
            _isSearching.update { false }
        }
}
