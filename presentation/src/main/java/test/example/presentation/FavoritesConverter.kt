package test.example.presentation

import test.example.domain.usecase.ShortenUrlUseCase
import javax.inject.Inject

class FavoritesConverter @Inject constructor(): ResultConverter<ShortenUrlUseCase.Response, FavoriteModel>() {

    override fun convertSuccess(data: ShortenUrlUseCase.Response) =
        FavoriteModel(
            id = data.alias.id,
            url = data.alias.urls.short
        )

}