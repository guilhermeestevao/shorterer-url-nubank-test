package test.example.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import test.example.domain.entity.Alias
import test.example.domain.usecase.FindByAliasUseCase
import test.example.domain.usecase.UseCases
import test.example.presentation.common.UiState
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val useCases: UseCases,
    val converter: AliasConverter
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Alias>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun findById(favoriteId: Long) {
        viewModelScope.launch {
            useCases.findById.execute(
                FindByAliasUseCase.Request(favoriteId)
            ).map{
                converter.convert(it)
            }.collect{
                _uiState.value = it
            }
        }

    }

}