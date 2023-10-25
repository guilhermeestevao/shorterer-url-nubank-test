package test.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import test.example.domain.entity.Favorite

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}