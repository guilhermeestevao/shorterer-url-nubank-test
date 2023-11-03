package test.example.data

import kotlinx.coroutines.flow.Flow
import test.example.data.source.remote.RemoteShortenerDataSource
import test.example.domain.ShortenerUrlRepository
import test.example.domain.entity.Alias
import test.example.domain.entity.Favorite
import javax.inject.Inject

class ShortenerUrlRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteShortenerDataSource
): ShortenerUrlRepository {

    override fun shortenUrl(favorite: Favorite): Flow<Alias> =
        remoteSource.shortenUrl(favorite)

    override fun findUrlByAlias(id: Long): Flow<Alias> =
        remoteSource.findUrlByAlias(id.toString())

}