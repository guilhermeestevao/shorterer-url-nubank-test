package test.example.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import test.example.domain.usecase.FindByAliasUseCase
import test.example.domain.usecase.GetAllFavorites
import test.example.domain.usecase.ShortenUrlUseCase
import test.example.domain.usecase.UseCases

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideUseCaseConfiguration() = ViewUseCaseContract.Configuration(Dispatchers.IO)

    @Provides
    fun provideUseCase(
        repository: ShortenerUrlRepository,
        configuration: ViewUseCaseContract.Configuration
    ): UseCases =
        UseCases(
            shortenUrl = ShortenUrlUseCase(repository, configuration),
            findById = FindByAliasUseCase(repository, configuration),
            getAllFavorites = GetAllFavorites(repository, configuration)
        )

}