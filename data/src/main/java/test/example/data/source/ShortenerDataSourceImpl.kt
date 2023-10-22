package test.example.data.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import test.example.data.ShortererUrlService
import test.example.data.toAlias
import test.example.data.toFavorite
import test.example.data.toUrlModel
import test.example.domain.ShortenerUseCaseException
import test.example.domain.entity.Alias
import test.example.domain.entity.Favorite

class ShortenerDataSourceImpl(
    private val service: ShortererUrlService
): ShortenerDataSource {

    override fun shortenUrl(favorite: Favorite): Flow<Alias> = flow {
        emit(service.shortenUrl(favorite.toUrlModel()))
    }.map {
        it.toAlias()
    }.catch {
        throw ShortenerUseCaseException(it)
    }

    override fun findUrlByAlias(id: String): Flow<Favorite> = flow {
        emit(service.findUrlByAlias(id))
    }.map {
        it.toFavorite()
    }.catch {
        throw ShortenerUseCaseException(it)
    }
}