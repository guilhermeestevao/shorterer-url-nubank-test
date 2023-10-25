package test.example.presentation

fun <T: Any> UiState<List<T>>.update(newItem: UiState<T>): UiState<List<T>> {
    return when(newItem) {
        is UiState.Success -> this.update(newItem.data)
        is UiState.Error -> UiState.Error(newItem.message)
        else -> this
    }
}

fun <T: Any> UiState<List<T>>.update(newItem: T): UiState<List<T>> {
    return when(this) {
        is UiState.Success -> UiState.Success(data + newItem)
        else -> UiState.Success(listOf(newItem))
    }
}