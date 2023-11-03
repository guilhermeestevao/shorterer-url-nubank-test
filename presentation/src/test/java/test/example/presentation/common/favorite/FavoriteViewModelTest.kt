package test.example.presentation.common.favorite

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import test.example.domain.entity.Alias
import test.example.domain.entity.Result
import test.example.domain.usecase.FindByAliasUseCase
import test.example.domain.usecase.ShortenUrlUseCase
import test.example.domain.usecase.UseCases
import test.example.presentation.common.UiState
import test.example.presentation.favorite.AliasConverter
import test.example.presentation.favorite.FavoriteViewModel

class FavoriteViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = UnconfinedTestDispatcher()
    private val converter = mock<AliasConverter>()
    private val shortnerUseCase = mock<ShortenUrlUseCase>()
    private val findByAliasUseCase = mock<FindByAliasUseCase>()
    private val useCases = UseCases(shortnerUseCase, findByAliasUseCase)

    private val viewModel = FavoriteViewModel(useCases, converter)


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() = runTest {
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearsDown() = runTest {
        Dispatchers.resetMain()
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testShortenUrlSuccess() = runTest {
        assertEquals(UiState.Loading, viewModel.uiState.value)
        val aliasId = 1L
        val uiState = mock<UiState.Success<Alias>>()
        val result = mock<Result.Success<FindByAliasUseCase.Response>>()
        whenever(useCases.findById.execute(FindByAliasUseCase.Request(aliasId)))
            .thenReturn(
                flowOf(
                    result
                )
            )
        whenever(converter.convert(result)).thenReturn(uiState)
        viewModel.findById(aliasId)
        assertEquals(uiState, viewModel.uiState.value)
    }

}
