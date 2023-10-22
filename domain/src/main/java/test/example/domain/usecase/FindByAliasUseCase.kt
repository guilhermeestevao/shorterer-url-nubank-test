package test.example.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test.example.domain.ShortenerUrlRepository
import test.example.domain.ViewUseCaseContract
import test.example.domain.entity.Favorite

class FindByAliasUseCase(
    private val repository: ShortenerUrlRepository,
    configuration: Configuration
): ViewUseCaseContract<FindByAliasUseCase.Request, FindByAliasUseCase.Response>(configuration) {

    data class Request(
        val id: String
    ): ViewUseCaseContract.Request

    data class Response(
        val favorite: Favorite
    ): ViewUseCaseContract.Response

    override fun process(request: Request): Flow<Response> =
        repository.findUrlByAlias(request.id)
            .map { Response(it) }

}