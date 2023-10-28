package test.example.domain.usecase

import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import test.example.domain.ShortenerUrlRepository
import test.example.domain.entity.Alias
import test.example.domain.entity.Favorite

class ShortenUrlUseCaseTest {

    private val repository = mock<ShortenerUrlRepository>()
    private val useCase = ShortenUrlUseCase(repository, mock())



}