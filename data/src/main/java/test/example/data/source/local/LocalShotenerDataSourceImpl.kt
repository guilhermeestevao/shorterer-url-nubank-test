package test.example.data.source.local

import test.example.data.local.db.FavoriteDao
import test.example.data.local.db.FavoriteEntity
import test.example.domain.entity.Alias
import javax.inject.Inject

class LocalShotenerDataSourceImpl @Inject constructor(
    private val dao: FavoriteDao
): LocalShotenerDataSource {
    override suspend fun getFavorites(): List<Alias> =
        dao.getFavorites().map { Alias(it.urlId, it.url) }

    override suspend fun addFavorite(alias: Alias) =
        dao.addFavorite(FavoriteEntity(alias.urlId, alias.url))
}