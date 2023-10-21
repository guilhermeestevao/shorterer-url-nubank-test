package test.example.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class ViewUseCaseContract<S: ViewUseCaseContract.Request, T: ViewUseCaseContract.Response>(
    private val configuration: Configuration
) {

    fun execute(request: S) = process(request)
        .map { Result.success(it) }
        .flowOn(configuration.dispatcher)
        .catch { emit(Result.failure(AliasUseCaseException(it))) }

    interface Request
    interface Response
    class Configuration(val dispatcher: CoroutineDispatcher)

    internal abstract fun process(request: S): Flow<T>
}