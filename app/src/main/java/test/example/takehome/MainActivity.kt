package test.example.takehome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import test.example.presentation.favorite.FavoriteScreen
import test.example.presentation.home.HomeScreen
import test.example.takehome.ui.theme.TakeHomeTestTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeHomeTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = stringResource(R.string.app_title))
                                },
                                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                            )
                        }
                    ) { values ->
                        val navController = rememberNavController()
                        App(navController, values)
                    }
                }
            }
        }
    }

}

@Composable
fun App(navController: NavHostController, values: PaddingValues) {

    NavHost(
        modifier = Modifier.padding(values),
        navController = navController,
        startDestination = Routes.Home.path
    ) {
        composable(route = Routes.Home.path) {
            HomeScreen(hiltViewModel()) {
                navController.navigate(Routes.Favorite.routeForFavorite(it))
            }
        }
        composable(
            route = Routes.Favorite.path,
            arguments = Routes.Favorite.args
        ) {
            val id = Routes.Favorite.fromEntry(it)
            FavoriteScreen(
                id = id,
                hiltViewModel()
            ) {
                navController.navigateUp()
            }
        }
    }

}



