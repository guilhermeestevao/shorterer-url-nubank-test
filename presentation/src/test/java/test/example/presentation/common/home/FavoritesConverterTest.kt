package test.example.presentation.common.home

import junit.framework.TestCase.assertEquals
import org.junit.Test
import test.example.domain.entity.Alias
import test.example.domain.usecase.ShortenUrlUseCase
import test.example.presentation.home.FavoritesConverter

class FavoritesConverterTest {

    private val converter = FavoritesConverter()

    @Test
    fun testConvertSuccess() {
        val responseAlias = Alias(1, "localhost:3000/1")
        val response = ShortenUrlUseCase.Response(
            responseAlias
        )

        val result = converter.convertSuccess(response)

        assertEquals(responseAlias.url, result)
    }

}