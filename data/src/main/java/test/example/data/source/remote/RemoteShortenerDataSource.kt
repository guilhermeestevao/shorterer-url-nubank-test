package test.example.data.source.remote

import kotlinx.coroutines.flow.Flow
import test.example.domain.entity.ShortenUrl
import test.example.domain.entity.Favorite

interface RemoteShortenerDataSource {
    fun shortenUrl(favorite: Favorite): Flow<ShortenUrl>
    fun findUrlByAlias(id: String): Flow<Favorite>
}