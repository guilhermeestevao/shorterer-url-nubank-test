package test.example.takehome.di

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import test.example.data.di.DataModule
import test.example.data.source.remote.RemoteShortenerDataSource
import test.example.takehome.mock.MockRemoteShortenerDataSource

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
abstract class MockDataSourceModule {

    @Binds
    abstract fun bindDatasource(dataSourceImpl: MockRemoteShortenerDataSource): RemoteShortenerDataSource

}