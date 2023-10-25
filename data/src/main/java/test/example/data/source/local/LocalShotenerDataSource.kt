package test.example.data.source.local

import test.example.domain.entity.Alias

interface LocalShotenerDataSource {
    suspend fun getFavorites(): List<Alias>
    suspend fun addFavorite(alias: Alias)
}