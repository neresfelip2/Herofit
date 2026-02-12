package br.com.neresfelip.herofitbrasil.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.neresfelip.herofitbrasil.presentation.ui.screen.LeagueDetailScreen
import br.com.neresfelip.herofitbrasil.presentation.ui.screen.LeagueListScreen
import br.com.neresfelip.herofitbrasil.presentation.ui.screen.MainScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Main.route
    ) {
        composable(Routes.Main.route) {
            MainScreen { sportName ->
                navController.navigate(
                    Routes.LeagueList.createRoute(
                        sportName
                    )
                )
            }
        }

        composable(
            route = Routes.LeagueList.route,
            arguments = listOf(
                navArgument(name = "name") { type = NavType.StringType }
            )
        ) {
            LeagueListScreen(
                onClickItem = { id ->
                    navController.navigate(
                        Routes.LeagueDetail.createRoute(
                            id
                        )
                    )
                },
                onClickBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.LeagueDetail.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            ),
        ) {
            LeagueDetailScreen(
                onClickBack = { navController.popBackStack() }
            )
        }

    }

}