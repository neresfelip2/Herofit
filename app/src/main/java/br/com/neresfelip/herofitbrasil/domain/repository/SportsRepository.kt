package br.com.neresfelip.herofitbrasil.domain.repository

import br.com.neresfelip.herofitbrasil.domain.model.League
import br.com.neresfelip.herofitbrasil.domain.model.Sport

interface SportsRepository {
    suspend fun getAllSports() : Result<List<Sport>>
    suspend fun getLeaguesBySportName(sportName: String) : Result<List<League>>
}