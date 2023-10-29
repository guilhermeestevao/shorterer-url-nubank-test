package test.example.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.example.data.ShortenerUrlRepositoryImpl
import test.example.data.source.remote.RemoteShortenerDataSource
import test.example.data.source.remote.RemoteShortenerDataSourceImpl
import test.example.domain.ShortenerUrlRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindRepository(repository: ShortenerUrlRepositoryImpl): ShortenerUrlRepository

    @Binds
    abstract fun bindRemoteDataSource(source: RemoteShortenerDataSourceImpl): RemoteShortenerDataSource

}