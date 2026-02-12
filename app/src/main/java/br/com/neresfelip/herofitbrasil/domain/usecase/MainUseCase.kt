package br.com.neresfelip.herofitbrasil.domain.usecase

import br.com.neresfelip.herofitbrasil.domain.model.Sport
import br.com.neresfelip.herofitbrasil.domain.repository.SportsRepository
import javax.inject.Inject

class MainUseCase @Inject constructor (
    private val sportsRepository: SportsRepository
) {

    suspend fun getAllSports() : Result<List<Sport>> {
        return sportsRepository.getAllSports()
    }

}