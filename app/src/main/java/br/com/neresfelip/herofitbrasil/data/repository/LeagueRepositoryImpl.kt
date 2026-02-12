package br.com.neresfelip.herofitbrasil.data.repository

import br.com.neresfelip.herofitbrasil.data.remote.SportAPI
import br.com.neresfelip.herofitbrasil.data.mapper.LeagueDetailMapper.toModel
import br.com.neresfelip.herofitbrasil.domain.model.LeagueDetail
import br.com.neresfelip.herofitbrasil.domain.repository.LeagueRepository

class LeagueRepositoryImpl(
    private val sportAPI: SportAPI
) : LeagueRepository {

    override suspend fun getLeagueDetail(id: Int): Result<LeagueDetail> {
        return try {
            val result = sportAPI.getLeagueDetail(id)
            Result.success(result.toModel())
        } catch(e: Exception) {
            val errorMessage = when (e) {
                is java.net.UnknownHostException,
                is java.net.ConnectException -> "Sem conexão com a internet"
                is java.net.SocketTimeoutException -> "Tempo de conexão esgotado"
                is IllegalStateException -> e.message ?: "Liga não encontrada"
                else -> e.message ?: "Erro desconhecido"
            }
            Result.failure(Exception(errorMessage))
        }
    }

}
