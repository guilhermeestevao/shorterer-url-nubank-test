package test.example.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.example.data.ShortenerUrlRepositoryImpl
import test.example.data.source.remote.ShortenerDataSource
import test.example.data.source.remote.ShortenerDataSourceImpl
import test.example.domain.ShortenerUrlRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: ShortenerUrlRepositoryImpl): ShortenerUrlRepository

    @Binds
    abstract fun bindDataSource(sourceImpl: ShortenerDataSourceImpl): ShortenerDataSource

}