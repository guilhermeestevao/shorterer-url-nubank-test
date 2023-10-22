package test.example.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import test.example.domain.entity.Favorite
import test.example.domain.usecase.ShortenUrlUseCase
import test.example.domain.usecase.UseCases
import javax.inject.Inject

@HiltViewModel
class ShortenerUrlViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {


    val list = MutableStateFlow(listOf<FavoriteItem>())

    fun getShorterUrl(url: String) {

        viewModelScope.launch {
            useCases.shortenUrl.execute(
                ShortenUrlUseCase.Request(
                    Favorite(url)
                )
            ).collect{
                if(it.isSuccess) {
                    it.onSuccess {
                        val favorite =  FavoriteItem(it.alias.id, it.alias.urls.short)
                        val newArrayList = ArrayList(list.value)
                        newArrayList.add(favorite)
                        list.value = newArrayList
                    }
                }
            }
        }

    }

}

typealias FavoriteItem = Pair<Long, String>