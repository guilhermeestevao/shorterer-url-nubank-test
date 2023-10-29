package test.example.domain.usecase

data class UseCases(
    val shortenUrl: ShortenUrlUseCase,
    val findById: FindByAliasUseCase,
    val getAllFavorites: GetAllFavorites
)