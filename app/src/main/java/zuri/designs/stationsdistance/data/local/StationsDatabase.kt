package zuri.designs.stationsdistance.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import zuri.designs.stationsdistance.data.local.dao.KoleoStationsDao
import zuri.designs.stationsdistance.data.model.Station
import zuri.designs.stationsdistance.data.model.StationKeyword

@Database(entities = [Station::class, StationKeyword::class], version = 1)
abstract class StationsDatabase : RoomDatabase() {

    abstract fun koleoStationsDao(): KoleoStationsDao
}
