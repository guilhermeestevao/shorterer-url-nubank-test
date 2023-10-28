package test.example.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import test.example.data.source.local.LocalShotenerDataSource
import test.example.data.source.remote.RemoteShortenerDataSource
import test.example.domain.ShortenerUrlRepository
import test.example.domain.entity.Alias
import test.example.domain.entity.Favorite
import javax.inject.Inject

class ShortenerUrlRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteShortenerDataSource,
    private val localSource: LocalShotenerDataSource
): ShortenerUrlRepository {
    override fun shortenUrl(favorite: Favorite): Flow<List<Alias>> = flow {
        val shorten = remoteSource.shortenUrl(favorite).first()
        localSource.addFavorite(Alias(shorten.id, shorten.urls.short))
        emit(localSource.getFavorites())
    }

    override fun findUrlByAlias(id: Long): Flow<Alias> =
        remoteSource.findUrlByAlias(id.toString())

}