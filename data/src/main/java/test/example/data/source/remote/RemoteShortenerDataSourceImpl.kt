package test.example.data.source.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import test.example.data.remote.ShortererUrlService
import test.example.data.remote.model.toAlias
import test.example.data.remote.model.toFavorite
import test.example.data.remote.model.toUrlModel
import test.example.domain.entity.ShortenerUseCaseException
import test.example.domain.entity.ShortenUrl
import test.example.domain.entity.Favorite
import javax.inject.Inject

class RemoteShortenerDataSourceImpl @Inject constructor(
    private val service: ShortererUrlService
): RemoteShortenerDataSource {

    override fun shortenUrl(favorite: Favorite): Flow<ShortenUrl> = flow {
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