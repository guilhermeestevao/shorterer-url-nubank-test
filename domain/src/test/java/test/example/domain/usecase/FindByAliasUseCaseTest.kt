package test.example.domain.usecase

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import test.example.domain.ShortenerUrlRepository
import test.example.domain.entity.Alias
class FindByAliasUseCaseTest {

    private val repository = mock<ShortenerUrlRepository>()
    private val useCase = FindByAliasUseCase(repository, mock())

    @ExperimentalCoroutinesApi
    @Test
    fun testProcess() = runTest {
        val request = FindByAliasUseCase.Request(1L)
        val alias = Alias(1L, "google.com")
        whenever(repository.findUrlByAlias(request.id)).thenReturn(flowOf(alias))
        val response = useCase.process(request).first()
        assertEquals(FindByAliasUseCase.Response(alias), response)
    }

}