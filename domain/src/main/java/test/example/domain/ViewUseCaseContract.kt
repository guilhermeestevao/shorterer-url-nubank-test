package test.example.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import test.example.domain.entity.ShortenerUseCaseException
import test.example.domain.entity.Result
abstract class ViewUseCaseContract<S: ViewUseCaseContract.Request, T: ViewUseCaseContract.Response>(
    private val configuration: Configuration
) {

    fun execute(request: S) = process(request)
        .map { Result.Success(it) as Result<T> }
        .flowOn(configuration.dispatcher)
        .catch { emit(Result.Error(ShortenerUseCaseException(it))) }

    interface Request
    interface Response
    class Configuration(val dispatcher: CoroutineDispatcher)

    internal abstract fun process(request: S): Flow<T>
}