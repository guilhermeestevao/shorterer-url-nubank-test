package test.example.presentation.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import test.example.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, onBackNavigation: (() -> Unit)? = null, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),

                navigationIcon = {
                    onBackNavigation?.let {
                        IconButton(onClick = it) {
                            Icon(Icons.Default.ArrowBack, stringResource(R.string.back_icon_content_description))
                        }
                    }
                }
            )
        }
    ) { values ->
        content(values)
    }
}