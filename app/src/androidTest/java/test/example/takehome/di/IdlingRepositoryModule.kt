package test.example.takehome.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import test.example.data.ShortenerUrlRepositoryImpl
import test.example.data.di.DataModule
import test.example.data.source.remote.RemoteShortenerDataSource
import test.example.domain.ShortenerUrlRepository
import test.example.takehome.idling.AppCountingIdlingResource
import test.example.takehome.mock.MockShortenerRepository
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
class IdlingRepositoryModule {

    @Singleton
    @Provides
    fun provideIdlingResource(): AppCountingIdlingResource =
        AppCountingIdlingResource("repository-idling")

    @Provides
    fun provideRepository(
        dataSource: RemoteShortenerDataSource,
        appCountingIdlingResource: AppCountingIdlingResource
    ): ShortenerUrlRepository = MockShortenerRepository(
        repository = ShortenerUrlRepositoryImpl(dataSource),
        countingIdlingResource = appCountingIdlingResource
    )

}