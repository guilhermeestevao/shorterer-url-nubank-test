package test.example.domain.usecase


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test.example.domain.ShortenerUrlRepository
import test.example.domain.ViewUseCaseContract
import test.example.domain.entity.Alias

class GetAllFavorites(
    private val repository: ShortenerUrlRepository,
    configuration: Configuration
): ViewUseCaseContract<GetAllFavorites.Request, GetAllFavorites.Response>(configuration){

    object Request: ViewUseCaseContract.Request
    data class Response(
        val favorites: List<Alias>
    ): ViewUseCaseContract.Response

    override fun process(request: Request): Flow<Response> =
        repository.getAllFavorites()
            .map { Response(it) }


}