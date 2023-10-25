package test.example.data.source.remote

import kotlinx.coroutines.flow.Flow
import test.example.domain.entity.Alias
import test.example.domain.entity.Favorite

interface ShortenerDataSource {
    fun shortenUrl(favorite: Favorite): Flow<Alias>
    fun findUrlByAlias(id: String): Flow<Favorite>
}