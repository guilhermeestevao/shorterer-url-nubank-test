package test.example.presentation.home


import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import test.example.domain.entity.Favorite
import test.example.domain.entity.Result
import test.example.domain.usecase.GetAllFavorites
import test.example.domain.usecase.ShortenUrlUseCase
import test.example.domain.usecase.UseCases
import test.example.presentation.common.UiState
import java.lang.Error
import javax.inject.Inject

@HiltViewModel
class HomelViewModel @Inject constructor(
    private val useCases: UseCases,
    private val converter: FavoritesConverter,
    private val converter2: FavoriteConverter2
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<String>>>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    init {
        getAllFavorites()
    }

    fun getShorterUrl(url: String) {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            useCases.shortenUrl.execute(
                ShortenUrlUseCase.Request(
                    Favorite(url)
                )
            ).map {
                converter.convert(it)
            }.collect {
                _uiState.value = it
            }
        }

    }

    private fun getAllFavorites(){
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            useCases.getAllFavorites.execute(
                GetAllFavorites.Request
            ).map {
                converter2.convert(it)
            }.collect{
                _uiState.value = it
            }
        }
    }

}

