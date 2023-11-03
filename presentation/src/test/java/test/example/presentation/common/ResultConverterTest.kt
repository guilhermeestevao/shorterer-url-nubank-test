package test.example.presentation.common

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import test.example.domain.entity.Result
import test.example.domain.entity.ShortenerUseCaseException


class ResultConverterTest {

    private val converter = object : ResultConverter<String, String>() {
        override fun convertSuccess(data: String): String {
            return "result${data}"
        }
    }

    @Test
    fun testConvertError() {
        val errorMessage = "error"
        val exception = mock<ShortenerUseCaseException>()
        whenever(exception.localizedMessage).thenReturn(errorMessage)
        val errorResult = Result.Error(exception)
        val result = converter.convert(errorResult)
        assertEquals(UiState.Error(errorMessage), result)
    }

    @Test
    fun testConvertSuccess() {
        val data = "data"
        val successResult = Result.Success(data)
        val result = converter.convert(successResult)
        assertEquals(UiState.Success("result${data}"), result)
    }

}