package test.example.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import test.example.domain.entity.Favorite
import test.example.domain.usecase.ShortenUrlUseCase
import test.example.domain.usecase.UseCases
import javax.inject.Inject

@HiltViewModel
class ShortenerUrlViewModel @Inject constructor(
    private val useCases: UseCases,
    private val converter: FavoritesConverter
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<FavoriteModel>>>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun getShorterUrl(url: String) {
        viewModelScope.launch {
            useCases.shortenUrl.execute(
                ShortenUrlUseCase.Request(
                    Favorite(url)
                )
            ).map {
                converter.convert(it)
            }.collect {
                _uiState.value = _uiState.value.update(it)
            }
        }

    }

}

