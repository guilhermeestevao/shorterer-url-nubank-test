package test.example.presentation.home

import android.util.Patterns
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction.Companion.Search
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import test.example.presentation.R
import test.example.presentation.common.CommonScreen
import test.example.presentation.common.ErrorDialog

@Composable
fun HomeScreen(
    viewModel: HomelViewModel,
    findById: (Long) -> Unit
) {

    val favoriteList = viewModel.favoriteList.toList()
    val errorState = viewModel.erroState.collectAsState()
    val loadingState = viewModel.loadingState.collectAsState()

    val onSubmit = { str: String ->
        str.toLongOrNull()?.let { id ->
            findById(id)
        } ?: run {
            viewModel.getShorterUrl(str)
        }
    }

    CommonScreen(
        title = stringResource(R.string.app_title)
    ) { values ->
        Content(
            modifier = Modifier.padding(values),
            favorites = favoriteList,
            onClose = viewModel::cleanErrorMessage,
            errorMessage = errorState
        ) {
            val isLoading =loadingState.value
            FavoriteForm(isLoading, onSubmit)
            Loading(isLoading)
            FavoriteList(it)
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    favorites: List<String>,
    errorMessage: State<String?>,
    onClose: () -> Unit,
    success: @Composable (List<String>) -> Unit,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
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
    LazyColumn{
        itemsIndexed(favorites) { index, favorite  ->
           FavoriteItem(favorite, index < favorites.lastIndex)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteForm(
    isLoading: Boolean,
    onAction: (String) -> Unit,
) {
    var urlTextLink by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(false) }

    val submitAction = {
        isValid = (Patterns.WEB_URL.matcher(urlTextLink).matches().or(urlTextLink.toULongOrNull() != null)).not()
        if(!isValid) {
            onAction(urlTextLink)
            urlTextLink = ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        TextField(
            value = urlTextLink,
            modifier = Modifier.fillMaxWidth().testTag(INPUT_TEXT_TAG),
            enabled = !isLoading,
            placeholder = {
                Text(text = stringResource(R.string.hint_url_text_field))
            },
            maxLines = 1,
            onValueChange = {
                urlTextLink = it
            },
            keyboardOptions = KeyboardOptions(
                imeAction = Search,
                keyboardType = KeyboardType.Uri
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    submitAction()
                }
            ),
            trailingIcon = {
                AnimatedVisibility(visible = urlTextLink.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            urlTextLink = ""
                            isValid = false
                        },
                        modifier = Modifier.testTag(CLEAR_ICON_TAG)
                    ) {
                        Icon(Icons.Default.Clear, stringResource(R.string.clear_icon_text))
                    }
                }
            },
            isError = isValid,
            supportingText = {
                AnimatedVisibility(visible = isValid) {
                    Text(
                        modifier = Modifier.testTag(ERROR_MENSSAGE_TAG),
                        text = stringResource(R.string.error_hint_message),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )
        AnimatedVisibility(
            visible = !isLoading,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
        ) {
            Button(
                modifier = Modifier.testTag(ADD_BUTTON_TAG),
                enabled = urlTextLink.isNotEmpty(),
                onClick = {
                    submitAction()
                }
            ) {
                Text(text = stringResource(R.string.add_favorite_text_button))
            }
        }
    }
}

@Composable
fun FavoriteItem(favorite: String, hasDivider: Boolean) {
    val alias = favorite.substringAfterLast("/")
    val baseUrl = favorite.substringBeforeLast("/")
    Column {
        Text(
            buildAnnotatedString {
                append(baseUrl)
                append("/")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)) {
                    append(alias)
                }
            },
            fontSize = 15.sp,
            modifier = Modifier.padding(vertical = 12.dp)
        )
        if(hasDivider)
            Divider(
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            )
    }
}

@Composable
fun Loading(visible: Boolean){
    if(visible) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

const val INPUT_TEXT_TAG = "url_input_tag"
const val ADD_BUTTON_TAG = "add_button_tag"
const val CLEAR_ICON_TAG = "clear_url_input_tag"
const val ERROR_MENSSAGE_TAG = "error_message_tag"