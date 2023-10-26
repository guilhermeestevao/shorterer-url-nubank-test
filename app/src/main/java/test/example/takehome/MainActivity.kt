package test.example.takehome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import test.example.presentation.FavoriteScreen
import test.example.presentation.ShortenerUrlViewModel
import test.example.takehome.ui.theme.TakeHomeTestTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeHomeTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    App(navController)
                }
            }
        }
    }

}

@Composable
fun App(navController: NavHostController) {
    val viewModel = hiltViewModel<ShortenerUrlViewModel>()
    val state by viewModel.uiState.collectAsState()
    NavHost(navController, startDestination = Routes.Home.path) {
        composable(route = Routes.Home.path) {
            FavoriteScreen(state, viewModel::getShorterUrl)
        }
    }
}
