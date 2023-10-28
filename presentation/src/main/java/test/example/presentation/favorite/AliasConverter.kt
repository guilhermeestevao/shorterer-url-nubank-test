package test.example.presentation.favorite

import test.example.domain.entity.Alias
import test.example.domain.usecase.FindByAliasUseCase
import test.example.presentation.common.ResultConverter
import javax.inject.Inject

class AliasConverter @Inject constructor(): ResultConverter<FindByAliasUseCase.Response, Alias>() {
    override fun convertSuccess(data: FindByAliasUseCase.Response): Alias =
        data.favorite

}