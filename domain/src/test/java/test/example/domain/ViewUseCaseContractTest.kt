package test.example.domain

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import test.example.domain.entity.Result
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import test.example.domain.entity.ShortenerUseCaseException

class ViewUseCaseContractTest {

    @ExperimentalCoroutinesApi
    private val configuration = ViewUseCaseContract.Configuration(UnconfinedTestDispatcher())
    private val request = mock<ViewUseCaseContract.Request>()
    private val response = mock<ViewUseCaseContract.Response>()
    private lateinit var useCase: ViewUseCaseContract<ViewUseCaseContract.Request, ViewUseCaseContract.Response>

    @Before
    fun setUp() {
        useCase = object : ViewUseCaseContract<ViewUseCaseContract.Request, ViewUseCaseContract.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@ViewUseCaseContractTest.request, request)
                return flowOf(response)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSuccess() = runTest {
        val result = useCase.execute(request).first()
        assertEquals(Result.Success(response), result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testException() {
        useCase = object : ViewUseCaseContract<ViewUseCaseContract.Request, ViewUseCaseContract.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@ViewUseCaseContractTest.request, request)
                return flow {
                    throw ShortenerUseCaseException(Throwable())
                }
            }

        }
        runTest {
            val result = useCase.execute(request).first()
            Assert.assertTrue((result as Result.Error).exception is ShortenerUseCaseException)
        }
    }
}