package test.example.presentation.common.favorite

import junit.framework.TestCase.assertEquals
import org.junit.Test
import test.example.domain.entity.Alias
import test.example.domain.usecase.FindByAliasUseCase
import test.example.presentation.favorite.AliasConverter

class AliasConverterTest {

    private val converter = AliasConverter()

    @Test
    fun testConvertSuccess() {
        val alias = Alias(1, "localhost:3000/1")
        val response = FindByAliasUseCase.Response(alias)
        val result =  converter.convertSuccess(response)
        assertEquals(alias, result)
    }

}