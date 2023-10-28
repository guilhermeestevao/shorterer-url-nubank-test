package test.example.data

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import test.example.data.source.local.LocalShotenerDataSource
import test.example.data.source.remote.RemoteShortenerDataSource
import test.example.domain.entity.Alias

class ShortenerUrlRepositoryImplTest {

    private val localDataSource = mock<LocalShotenerDataSource>()
    private val remoteSource = mock<RemoteShortenerDataSource>()
    private val repository = ShortenerUrlRepositoryImpl(remoteSource, localDataSource)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testFindByAlias() = runTest {
        val id = 123L
        val alias = Alias(id, "google.com")
        whenever(repository.findUrlByAlias(id)).thenReturn(flowOf(alias))
        val result = repository.findUrlByAlias(id).first()
        assertEquals(alias, result)
    }

/*    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testShortenUrl() = runTest {
        val favorite = Favorite("google.com")
        val alias = Alias(1L, "google.com/1")
        val list = listOf(alias)
        whenever(repository.shortenUrl(favorite)).thenReturn(flowOf(list))
        val result = repository.shortenUrl(favorite).first()
        Assert.assertEquals(list, result)
        verify(localDataSource).addFavorite(alias)
    }*/

}