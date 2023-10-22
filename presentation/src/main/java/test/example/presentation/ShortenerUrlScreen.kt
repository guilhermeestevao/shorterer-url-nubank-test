package test.example.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    viewModel: ShortenerUrlViewModel = viewModel()
) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        FavoriteForm(viewModel)
        FavoriteList(viewModel)
    }

}

@Composable
fun FavoriteList(
    viewModel: ShortenerUrlViewModel
) {

    val favorites = viewModel.list.collectAsState()

    favorites.value.let {
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            items(it) { item ->
                val (id, url) = item
                Column{
                    Text(text = id.toString())
                    Text(text = url)
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteForm(
    viewModel: ShortenerUrlViewModel
) {
    var text by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        OutlinedTextField(
            value = text,
            onValueChange = {text = it}
        )
        IconButton(onClick = {
            viewModel.getShorterUrl(text)
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