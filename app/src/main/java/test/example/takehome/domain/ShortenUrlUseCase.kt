package test.example.takehome.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShortenUrlUseCase(
    private val repository: AliasRepository,
    configuration: Configuration
): ViewUseCaseContract<ShortenUrlUseCase.Request, ShortenUrlUseCase.Response>(configuration) {

    operator fun invoke(urlDto: UrlDto) = process(Request(urlDto))

    override fun process(request: Request): Flow<Response> =
        repository.shortenUrl(request.urlDto)
            .map { Response(it) }

    data class Request(
        val urlDto: UrlDto
    ): ViewUseCaseContract.Request
    data class Response(
        val alias: AliasDto
    ): ViewUseCaseContract.Response

}