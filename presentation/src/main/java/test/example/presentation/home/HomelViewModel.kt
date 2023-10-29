package test.example.presentation.home

import androidx.compose.runtime.mutableStateListOf
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
import test.example.presentation.common.UiState
import javax.inject.Inject

@HiltViewModel
class HomelViewModel @Inject constructor(
    private val useCases: UseCases,
    private val converter: FavoritesConverter
): ViewModel() {

    val favoriteList = mutableStateListOf<String>()

    private val _error = MutableStateFlow<String?>(null)
    val erroState = _error.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loadingState = _loading.asStateFlow()

    fun getShorterUrl(url: String) {
        _loading.value = true
        viewModelScope.launch {
            useCases.shortenUrl.execute(
                ShortenUrlUseCase.Request(
                    Favorite(url)
                )
            ).map {
                converter.convert(it)
            }.collect{
                _loading.value = false
                when(it){
                    is UiState.Success -> {
                        favoriteList.add(0, it.data)
                    }
                    is UiState.Error -> {
                        _error.value = it.message
                    }
                    else -> {}
                }
            }
        }

    }

    fun cleanErrorMessage() {
        _error.value = null
    }

}

