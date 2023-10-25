package test.example.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class DatabaseFavorites: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}