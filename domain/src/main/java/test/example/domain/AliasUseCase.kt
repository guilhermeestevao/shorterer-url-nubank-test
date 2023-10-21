package test.example.domain

data class AliasUseCase(
    val shortenUrl: ShortenUrlUseCase,
    val findById: FindByAliasUseCase
)