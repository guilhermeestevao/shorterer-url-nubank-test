package test.example.presentation.home

import test.example.domain.usecase.GetAllFavorites
import test.example.domain.usecase.ShortenUrlUseCase
import test.example.presentation.common.ResultConverter
import javax.inject.Inject

class FavoriteConverter2 @Inject constructor(): ResultConverter<GetAllFavorites.Response, List<String>>() {

    override fun convertSuccess(data: GetAllFavorites.Response) =
        data.favorites.map {it.url}

}