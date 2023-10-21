package test.example.takehome.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FindByAliasUseCase(
    private val repository: AliasRepository,
    configuration: Configuration
): ViewUseCaseContract<FindByAliasUseCase.Request, FindByAliasUseCase.Response>(configuration) {

    data class Request(
        val id: String
    ): ViewUseCaseContract.Request

    data class Response(
        val urlDto: UrlDto
    ): ViewUseCaseContract.Response

    override fun process(request: Request): Flow<Response> =
        repository.findUrlByAlias(request.id)
            .map { Response(it) }

}