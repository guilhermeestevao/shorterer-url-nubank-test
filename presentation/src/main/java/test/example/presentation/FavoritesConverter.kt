package test.example.presentation

import test.example.domain.usecase.ShortenUrlUseCase
import javax.inject.Inject

class FavoritesConverter @Inject constructor(): ResultConverter<ShortenUrlUseCase.Response, List<FavoriteModel>>() {

    override fun convertSuccess(data: ShortenUrlUseCase.Response) =
        data.favorites.map {
            FavoriteModel(
                id = it.urlId,
                url = it.url
            )
        }

}