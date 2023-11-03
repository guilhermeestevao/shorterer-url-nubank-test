package test.example.presentation.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import test.example.domain.entity.Alias
import test.example.presentation.common.ErrorDialog
import test.example.presentation.common.UiState
import test.example.presentation.common.CommonScreen

@Composable
fun FavoriteScreen(
    id: Long,
    viewModel: FavoriteViewModel,
    onBack: () -> Unit
){
    LaunchedEffect(true) {
        viewModel.findById(id)
    }

    val state by viewModel.uiState.collectAsState()

    CommonScreen(title = id.toString(), onBackNavigation = onBack) {
        Content(
            modifier = Modifier.padding(it),
            state = state,
            onCloseErrorDialog = onBack
        ) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentSize(Alignment.Center),
                    text = it.url)
            }
        }
    }

}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    state: UiState<Alias>,
    onCloseErrorDialog: () -> Unit,
    success: @Composable (Alias) -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        when(state) {
            is UiState.Success -> success(state.data)
            is UiState.Loading -> {
                CircularProgressIndicator()
            }
            is UiState.Error -> {
                ErrorDialog(state.message, onCloseErrorDialog)
            }
        }
    }
}