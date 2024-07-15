package zuri.designs.stationsdistance.data.remote

import retrofit2.http.GET
import zuri.designs.stationsdistance.data.model.StationJson
import zuri.designs.stationsdistance.data.model.StationKeywordJson

interface KoleoApi {

    @GET("stations")
    suspend fun getStations(): List<StationJson>

    @GET("station_keywords")
    suspend fun getStationKeywords(): List<StationKeywordJson>
}
