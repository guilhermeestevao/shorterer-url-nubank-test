package test.example.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test.example.domain.ShortenerUrlRepository
import test.example.domain.ViewUseCaseContract
import test.example.domain.entity.Alias


class FindByAliasUseCase(
    private val repository: ShortenerUrlRepository,
    configuration: Configuration
): ViewUseCaseContract<FindByAliasUseCase.Request, FindByAliasUseCase.Response>(configuration) {

    data class Request(
        val id: Long
    ): ViewUseCaseContract.Request

    data class Response(
        val favorite: Alias
    ): ViewUseCaseContract.Response

    override fun process(request: Request): Flow<Response> =
        repository.findUrlByAlias(request.id)
            .map { Response(it) }

}