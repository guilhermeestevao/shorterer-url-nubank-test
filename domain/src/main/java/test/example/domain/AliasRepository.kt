package test.example.domain

import kotlinx.coroutines.flow.Flow

interface AliasRepository {
    fun shortenUrl(urlDto: UrlDto): Flow<AliasDto>
    fun findUrlByAlias(id: String): Flow<UrlDto>
}