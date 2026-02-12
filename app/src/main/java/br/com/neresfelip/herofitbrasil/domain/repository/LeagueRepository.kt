package br.com.neresfelip.herofitbrasil.domain.repository

import br.com.neresfelip.herofitbrasil.domain.model.LeagueDetail

interface LeagueRepository {
    suspend fun getLeagueDetail(id: Int): Result<LeagueDetail>
}