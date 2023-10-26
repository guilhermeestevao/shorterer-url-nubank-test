package test.example.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FavoriteScreen(
    state: UiState<List<FavoriteModel>>,
    action: (String) -> Unit
) {
    Content(
        action = action,
        state = state
    ) {
        FavoriteList(it)
    }
}

@Composable
fun <T: Any>Content(
    state: UiState<T>,
    action: (String) -> Unit,
    child: @Composable (T) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ){
        FavoriteForm(action)
        when(state) {
            is UiState.Success -> {
                child(state.data)
            }
            is UiState.Error -> {
                Error()
            }
            is UiState.Loading -> {
                Loading()
            }
            is UiState.Idle -> {}
        }

    }
}

@Composable
fun FavoriteList(
    favorites: List<FavoriteModel>
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
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
fun FavoriteItem(favorite: FavoriteModel) {
    Column {
        Text(text = favorite.id.toString())
        Text(text = favorite.url)
    }
}

@Composable
fun Loading(){
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth()

    )
}

@Composable
fun Error() {

}