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
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        return keyword.replacePolishSigns().contains(query.replacePolishSigns())
    }
}

fun StationKeywordJson.toStationKeyword() = StationKeyword(
    id = id,
    keyword = keyword,
    stationId = stationId
)

fun String.replacePolishSigns(): String {
    // temporary workaround, only for Polish signs (Check and German letters not covered for now)
    // it will be fixed
    return this
        .lowercase()
        .replace("ą", "a")
        .replace("ć", "c")
        .replace("ę", "e")
        .replace("ł", "l")
        .replace("ń", "n")
        .replace("ó", "o")
        .replace("ś", "s")
        .replace("ź", "z")
        .replace("ż", "z")
}
