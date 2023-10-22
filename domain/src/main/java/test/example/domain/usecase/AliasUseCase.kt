package test.example.domain.usecase

data class AliasUseCase(
    val shortenUrl: ShortenUrlUseCase,
    val findById: FindByAliasUseCase
)