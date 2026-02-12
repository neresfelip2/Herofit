package br.com.neresfelip.herofitbrasil.domain.usecase

import br.com.neresfelip.herofitbrasil.domain.model.League
import br.com.neresfelip.herofitbrasil.domain.repository.SportsRepository
import javax.inject.Inject

class LeagueListUseCase @Inject constructor(
    private val sportsRepository: SportsRepository
) {

    suspend fun getLeaguesBySportName(sportName: String): Result<List<League>> {
        return sportsRepository.getLeaguesBySportName(sportName)
    }

}