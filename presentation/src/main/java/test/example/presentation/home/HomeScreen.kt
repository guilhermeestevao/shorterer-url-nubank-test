package test.example.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import test.example.presentation.common.ErrorDialog
import test.example.presentation.common.UiState

@Composable
fun HomeScreen(
    viewModel: HomelViewModel,
    findById: (Long) -> Unit
) {

    val state by  viewModel.uiState.collectAsState()

    val action = {str: String ->
        str.toLongOrNull()?.let { id ->
            findById(id)
        }?: run {
            viewModel.getShorterUrl(str)
        }
    }

    Content(
        action = action,
        state = state
    ) {
        FavoriteList(it)
    }
}

@Composable
fun Content(
    state: UiState<List<String>>,
    action: (String) -> Unit,
    success: @Composable (List<String>) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ){
        FavoriteForm(action)
        when(state) {
            is UiState.Success -> {
                success(state.data)
            }
            is UiState.Error -> {
                ErrorDialog(state.message)
            }
            is UiState.Loading -> {
                Loading()
            }
            else -> {}
        }
    }
}

@Composable
fun FavoriteList(
    favorites: List<String>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        items(favorites) { favorite ->
           FavoriteItem(favorite)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteForm(
    onAction: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        OutlinedTextField(
            value = text,
            onValueChange = { text = it }
        )
        IconButton(onClick = {
            onAction(text)
            text = ""
        }) {
            Icon(
                Icons.Filled.Search,
                "buscar",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Composable
fun FavoriteItem(favorite: String) {
    Column {
        Text(
            text = favorite,
            fontSize = 14.sp
        )
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
    }
}

@Composable
fun Loading(){
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth()
    )
}

