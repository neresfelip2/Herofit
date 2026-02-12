package br.com.neresfelip.herofitbrasil.data.remote

import br.com.neresfelip.herofitbrasil.data.remote.response.AllSportsResponse
import br.com.neresfelip.herofitbrasil.data.remote.response.LeagueDetailResponse
import br.com.neresfelip.herofitbrasil.data.remote.response.LeagueListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SportAPI {

    @GET("all_sports.php")
    suspend fun getAllSports() : AllSportsResponse

    @GET("search_all_leagues.php")
    suspend fun getLeaguesBySportName(@Query("s") sportName: String): LeagueListResponse

    @GET("lookupleague.php")
    suspend fun getLeagueDetail(@Query("id") id: Int): LeagueDetailResponse

}