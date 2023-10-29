package test.example.presentation.home

import test.example.domain.usecase.ShortenUrlUseCase
import test.example.presentation.common.ResultConverter
import javax.inject.Inject

class FavoritesConverter @Inject constructor(): ResultConverter<ShortenUrlUseCase.Response, String>() {

    override fun convertSuccess(data: ShortenUrlUseCase.Response) =
        data.alias.url

}