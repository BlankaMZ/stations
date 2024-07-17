package zuri.designs.stationsdistance.data.repository

import kotlinx.coroutines.flow.Flow
import zuri.designs.stationsdistance.data.local.StationsDatabase
import zuri.designs.stationsdistance.data.model.Station
import zuri.designs.stationsdistance.data.model.StationKeyword
import zuri.designs.stationsdistance.data.model.toStation
import zuri.designs.stationsdistance.data.model.toStationKeyword
import zuri.designs.stationsdistance.data.remote.KoleoApi
import javax.inject.Inject

class Repository @Inject constructor(
    private val koleoApi: KoleoApi,
    private val stationsDatabase: StationsDatabase
) {

    private val koleoStationsDao = stationsDatabase.koleoStationsDao()

    suspend fun updateAllStations() {
        val stations = koleoApi.getStations().map { jsonStation ->
            jsonStation.toStation()
        }
        koleoStationsDao.addAllStations(stations = stations)
    }

    suspend fun updateAllStationKeywords() {
        val stationKeywords = koleoApi.getStationKeywords().map { keywordJson ->
            keywordJson.toStationKeyword()
        }
        koleoStationsDao.addAllStationKeywords(stationKeywords = stationKeywords)
    }

    val keywords: Flow<List<StationKeyword>>
        get() = koleoStationsDao.getAllStationKeywords()

    suspend fun getSearchedStations(ids: List<Int>): List<Station> {
        return koleoStationsDao.findByStationIds(ids).sortedByDescending { station -> station.hits }
    }

    suspend fun getChosenStation(id: Int): Station {
        return koleoStationsDao.getStation(id)
    }
}
