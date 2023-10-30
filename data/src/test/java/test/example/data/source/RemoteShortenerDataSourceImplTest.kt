package test.example.data.source

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import test.example.data.remote.ShortererUrlService
import test.example.data.remote.model.AliasModel
import test.example.data.remote.model.LinksModel
import test.example.data.remote.model.UrlModel
import test.example.data.source.remote.RemoteShortenerDataSourceImpl
import test.example.domain.entity.Alias
import test.example.domain.entity.Favorite
import test.example.domain.entity.ShortenUrl
import test.example.domain.entity.ShortenerUseCaseException
import test.example.domain.entity.Urls
class RemoteShortenerDataSourceImplTest {

    private val service = mock<ShortererUrlService>()
    val source = RemoteShortenerDataSourceImpl(service, mock())

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testShortenUrl() = runTest{

        val id = "1"
        val url = "google.com"
        val short = "host.com/1"
        val links = LinksModel(
            self = url,
            short = short
        )

        val alias = AliasModel(id, links)
        val shortenUrl = ShortenUrl(id.toLong(), urls = Urls(url, short))

        whenever(service.shortenUrl(any())).thenReturn(alias)
        val result = source.shortenUrl(Favorite(url)).first()
        assertEquals(shortenUrl, result)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testShortenUrlThrowsError() = runTest {
        whenever(service.shortenUrl(UrlModel("google.com"))).thenThrow(RuntimeException())
        source.shortenUrl(Favorite("google.com")).catch {
            assertTrue(it is ShortenerUseCaseException)
        }.collect()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testFindById() = runTest {
        val id = "1"
        val espected = Alias(id.toLong(), "google.com")
        whenever(service.findUrlByAlias(id)).thenReturn(UrlModel("google.com"))
        val result = source.findUrlByAlias(id).first()
        assertEquals(espected, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testFindByIdThrowsError() = runTest {
        whenever(service.findUrlByAlias(any())).thenThrow(RuntimeException())
        source.findUrlByAlias("1")
            .catch {  assertTrue(it is ShortenerUseCaseException) }
            .collect()
    }

}