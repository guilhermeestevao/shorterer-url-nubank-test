package test.example.presentation

import test.example.domain.ViewUseCaseContract
import test.example.domain.entity.Result
abstract class ResultConverter<T : ViewUseCaseContract.Response, R : Any> {

    fun convert(result: Result<T>): UiState<R> {
        return when(result) {
            is Result.Success -> {
                UiState.Success(convertSuccess(result.data))
            }
            is Result.Error -> {
                UiState.Error(result.exception.localizedMessage.orEmpty())
            }

        }
    }

    abstract fun convertSuccess(data: T): R

}