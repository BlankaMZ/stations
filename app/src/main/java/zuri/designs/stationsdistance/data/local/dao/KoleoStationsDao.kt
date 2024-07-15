package zuri.designs.stationsdistance.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import zuri.designs.stationsdistance.data.model.Station
import zuri.designs.stationsdistance.data.model.StationKeyword

@Dao
interface KoleoStationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllStations(stations: List<Station>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllStationKeywords(stationKeywords: List<StationKeyword>)

    @Query("SELECT * FROM station_keyword_table")
    fun getAllStationKeywords(): Flow<List<StationKeyword>>

    @Query("SELECT * FROM station_keyword_table where keyword LIKE :letters||'%' ")
    suspend fun getStationKeywords(letters: String): List<StationKeyword>

    @Query("SELECT * FROM stations_table WHERE id =:id")
    suspend fun getStation(id: Int): Station
}
