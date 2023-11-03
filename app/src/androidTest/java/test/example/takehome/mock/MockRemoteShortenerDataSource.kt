package test.example.takehome.mock

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import test.example.data.source.remote.RemoteShortenerDataSource
import test.example.domain.entity.Alias
import test.example.domain.entity.Favorite
import javax.inject.Inject

class MockRemoteShortenerDataSource @Inject constructor(): RemoteShortenerDataSource {
    override fun shortenUrl(favorite: Favorite): Flow<Alias> =
        flowOf(Alias(1L, "localhost:3000/1"))

    override fun findUrlByAlias(id: String): Flow<Alias> =
        flowOf(Alias(1L, "localhost:3000/1"))


}