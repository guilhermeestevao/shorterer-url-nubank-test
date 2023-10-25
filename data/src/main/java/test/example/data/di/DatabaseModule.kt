package test.example.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import test.example.data.local.db.DatabaseFavorites

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): DatabaseFavorites =
        Room.databaseBuilder(
            context,
            DatabaseFavorites::class.java, "favorites-database"
        ).build()

    @Provides
    fun providesDao(databaseFavorites: DatabaseFavorites) = databaseFavorites.favoriteDao()
}