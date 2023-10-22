package test.example.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test.example.domain.ShortenerUrlRepository
import test.example.domain.ViewUseCaseContract
import test.example.domain.entity.Alias
import test.example.domain.entity.Favorite

class ShortenUrlUseCase(
    private val repository: ShortenerUrlRepository,
    configuration: Configuration
): ViewUseCaseContract<ShortenUrlUseCase.Request, ShortenUrlUseCase.Response>(configuration) {

    operator fun invoke(favorite: Favorite) = process(Request(favorite))

    override fun process(request: Request): Flow<Response> =
        repository.shortenUrl(request.favorite)
            .map { Response(it) }

    data class Request(
        val favorite: Favorite
    ): ViewUseCaseContract.Request
    data class Response(
        val alias: Alias
    ): ViewUseCaseContract.Response

}