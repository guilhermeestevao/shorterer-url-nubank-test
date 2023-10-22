package test.example.data

import kotlinx.coroutines.flow.Flow
import test.example.data.source.ShortenerDataSource
import test.example.domain.ShortenerUrlRepository
import test.example.domain.entity.Alias
import test.example.domain.entity.Favorite

class ShortenerUrlRepositoryImpl(
    private val source: ShortenerDataSource
): ShortenerUrlRepository {
    override fun shortenUrl(favorite: Favorite): Flow<Alias> =
        source.shortenUrl(favorite)

    override fun findUrlByAlias(id: String): Flow<Favorite> =
        source.findUrlByAlias(id)

}