package br.com.neresfelip.herofitbrasil.domain.usecase

import br.com.neresfelip.herofitbrasil.domain.repository.LeagueRepository
import javax.inject.Inject

class LeagueDetailUseCase @Inject constructor(
    private val repository: LeagueRepository
) {
    suspend fun getLeagueDetail(id: Int) = repository.getLeagueDetail(id)
}
