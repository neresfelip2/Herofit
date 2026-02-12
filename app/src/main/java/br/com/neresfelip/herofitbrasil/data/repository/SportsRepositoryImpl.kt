package br.com.neresfelip.herofitbrasil.data.repository

import br.com.neresfelip.herofitbrasil.data.remote.SportAPI
import br.com.neresfelip.herofitbrasil.data.mapper.LeagueMapper.toDomain
import br.com.neresfelip.herofitbrasil.data.mapper.SportMapper.toDomain
import br.com.neresfelip.herofitbrasil.domain.model.League
import br.com.neresfelip.herofitbrasil.domain.model.Sport
import br.com.neresfelip.herofitbrasil.domain.repository.SportsRepository
import javax.inject.Inject

class SportsRepositoryImpl @Inject constructor(
    private val sportsAPI: SportAPI
) : SportsRepository {

    override suspend fun getAllSports(): Result<List<Sport>> {
        return try {
            val result = sportsAPI.getAllSports()
            Result.success(result.sports.map { it.toDomain() })
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is java.net.UnknownHostException,
                is java.net.ConnectException -> "Sem conexão com a internet"
                is java.net.SocketTimeoutException -> "Tempo de conexão esgotado"
                else -> e.message ?: "Erro desconhecido"
            }
            Result.failure(Exception(errorMessage))
        }
    }

    override suspend fun getLeaguesBySportName(sportName: String): Result<List<League>> {
        return try {
            val result = sportsAPI.getLeaguesBySportName(sportName)
            Result.success(result.leagues.map { it.toDomain() })
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is java.net.UnknownHostException,
                is java.net.ConnectException -> "Sem conexão com a internet"
                is java.net.SocketTimeoutException -> "Tempo de conexão esgotado"
                else -> e.message ?: "Erro desconhecido"
            }
            Result.failure(Exception(errorMessage))
        }
    }

}