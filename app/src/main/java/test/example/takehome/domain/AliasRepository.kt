package test.example.takehome.domain

import kotlinx.coroutines.flow.Flow

interface AliasRepository {
    fun shortenUrl(urlDto: UrlDto): Flow<AliasDto>
    fun findUrlByAlias(id: String): Flow<UrlDto>
}