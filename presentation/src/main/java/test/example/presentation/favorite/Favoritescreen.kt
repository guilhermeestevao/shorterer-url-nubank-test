package test.example.presentation.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import test.example.domain.entity.Alias
import test.example.presentation.common.ErrorDialog
import test.example.presentation.common.UiState

@Composable
fun FavoriteScreen(
    id: Long,
    viewModel: FavoriteViewModel
){
    LaunchedEffect(true) {
        viewModel.findById(id)
    }

    val state by viewModel.uiState.collectAsState()

    Content(state = state) {
        Text(text = it.url)
    }
}

@Composable
fun Content(
    state: UiState<Alias>,
    success: @Composable (Alias) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        when(state) {
            is UiState.Success -> success(state.data)
            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                ErrorDialog(state.message) {

                }
            }

            else -> {
1
            }
        }
    }
}