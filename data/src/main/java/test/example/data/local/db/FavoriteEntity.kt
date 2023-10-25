package test.example.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity (
    @PrimaryKey @ColumnInfo(name = "url_id") val urlId: Long,
    @ColumnInfo(name = "url") val url: String
)