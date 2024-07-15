package zuri.designs.stationsdistance.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import zuri.designs.stationsdistance.data.local.StationsDatabase
import zuri.designs.stationsdistance.util.Constants.KOLEO_STATIONS_DATABASE
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): StationsDatabase {
        return Room.databaseBuilder(
            context,
            StationsDatabase::class.java,
            KOLEO_STATIONS_DATABASE
        ).build()
    }
}
