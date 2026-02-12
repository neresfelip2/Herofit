package br.com.neresfelip.herofitbrasil.data.mapper

import br.com.neresfelip.herofitbrasil.data.remote.response.LeagueResponse
import br.com.neresfelip.herofitbrasil.domain.model.League
import br.com.neresfelip.herofitbrasil.presentation.model.LeagueUI

object LeagueMapper {

    fun LeagueResponse.toDomain() : League {
        return League(
            id = id,
            name = name,
            sport = sport,
            country = country ?: "",
            urlLogo = logo,
        )
    }

    fun List<League>.toUI(): List<LeagueUI> {
        return map {
            LeagueUI(
                id = it.id,
                name = it.name,
                urlImage = it.urlLogo,
            )
        }
    }

}