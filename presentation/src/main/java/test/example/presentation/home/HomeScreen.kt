package test.example.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import test.example.presentation.common.ErrorDialog

@Composable
fun HomeScreen(
    viewModel: HomelViewModel,
    findById: (Long) -> Unit
) {

    val favoriteList = viewModel.favoriteList.toList()
    val errorState = viewModel.erroState.collectAsState()
    val loadingState = viewModel.loadingState.collectAsState()
    val action = {str: String ->
        str.toLongOrNull()?.let { id ->
            findById(id)
        }?: run {
            viewModel.getShorterUrl(str)
        }
    }

    Content(
        favorites = favoriteList,
        onClose = viewModel::cleanErrorMessage,
        errorMessage = errorState
    ) {
        FavoriteForm(action)
        Loading(loadingState.value)
        FavoriteList(it)
    }
}

@Composable
fun Content(
    favorites: List<String>,
    errorMessage: State<String?>,
    onClose: () -> Unit,
    success: @Composable (List<String>) -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        success(favorites)
        errorMessage.value?.let {
            ErrorDialog(it, onClose)
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
fun Loading(visible: Boolean){
    if(visible) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

