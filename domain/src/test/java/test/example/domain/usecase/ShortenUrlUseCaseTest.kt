package test.example.domain.usecase

import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import test.example.domain.ShortenerUrlRepository
import test.example.domain.entity.Alias
import test.example.domain.entity.Favorite

class ShortenUrlUseCaseTest {

    private val repository = mock<ShortenerUrlRepository>()
    private val useCase = ShortenUrlUseCase(repository, mock())

    @ExperimentalCoroutinesApi
    @Test
    fun testProcess() = runTest {
        val favorite = Favorite("google.com")
        val alias = Alias(1L, "google.com/1")
        val request = ShortenUrlUseCase.Request(favorite)
        whenever(repository.shortenUrl(any())).thenReturn(flowOf(alias))
        val response = useCase.process(request).first()
        assertEquals(ShortenUrlUseCase.Response(alias), response)
    }

}