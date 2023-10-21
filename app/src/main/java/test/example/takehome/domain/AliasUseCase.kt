package test.example.takehome.domain

data class AliasUseCase(
    val shortenUrl: ShortenUrlUseCase,
    val findById: FindByAliasUseCase
)