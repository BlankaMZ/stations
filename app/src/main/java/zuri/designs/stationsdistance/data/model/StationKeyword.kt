package zuri.designs.stationsdistance.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import zuri.designs.stationsdistance.util.Constants

@Serializable
data class StationKeywordJson(
    val id: Int,
    val keyword: String,
    @SerialName("station_id")
    val stationId: Int

)

@Serializable
@Entity(tableName = Constants.STATION_KEYWORD_TABLE)
data class StationKeyword(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val keyword: String,
    val stationId: Int
)

fun StationKeywordJson.toStationKeyword() = StationKeyword(
    id = id,
    keyword = keyword,
    stationId = stationId
)
