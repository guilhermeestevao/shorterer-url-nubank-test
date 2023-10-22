package test.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.example.domain.entity.Favorite
import test.example.domain.usecase.ShortenUrlUseCase
import test.example.domain.usecase.UseCases
import javax.inject.Inject

@HiltViewModel
class ShortenerUrlViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    fun getShorterUrl(url: String) {

        viewModelScope.launch {
            useCases.shortenUrl.execute(
                ShortenUrlUseCase.Request(
                    Favorite(url)
                )
            )
        }

    }

}