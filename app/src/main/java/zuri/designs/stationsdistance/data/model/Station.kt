package zuri.designs.stationsdistance.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import zuri.designs.stationsdistance.util.Constants.STATIONS_TABLE

@Serializable
data class StationJson(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val hits: Int
)

@Serializable
@Entity(tableName = STATIONS_TABLE)
data class Station(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val hits: Int
)

fun StationJson.toStation() = Station(
    id = id,
    name = name,
    latitude = latitude,
    longitude = longitude,
    hits = hits
)

enum class StationType {
    ORIGIN, DESTINATION
}
