package br.com.neresfelip.herofitbrasil.presentation.navigation

sealed class Routes(val route: String) {

    object Main : Routes("main")

    object LeagueList : Routes("league_list/{name}") {
        fun createRoute(name: String) = "league_list/$name"
    }

    object LeagueDetail: Routes("league_detail/{id}") {
        fun createRoute(id: Int) = "league_detail/$id"
    }

}