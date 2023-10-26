package test.example.takehome

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val ROUTE_HOME= "home"
private const val ROUTE_FAVORITE = "favorite"
private const val ARG_FAVORITE_ID = "favoriteId"

sealed class Routes(
    val path: String,
    val args: List<NamedNavArgument> = emptyList()
) {

    object Home: Routes(ROUTE_HOME)
    object Favorite: Routes(
        path = String.format(ROUTE_FAVORITE, "{$ARG_FAVORITE_ID}"),
        args = listOf(
            navArgument(ARG_FAVORITE_ID) {
                type = NavType.LongType
            }
        )
    ){
        fun routeForFavorite(id: Long) = String.format(ROUTE_FAVORITE, id)

        fun fromEntry(entry: NavBackStackEntry): Long = entry.arguments?.getLong(ARG_FAVORITE_ID) ?: 0L

    }

}