package br.com.neresfelip.herofitbrasil.data.mapper

import br.com.neresfelip.herofitbrasil.data.remote.response.LeagueDetailResponse
import br.com.neresfelip.herofitbrasil.domain.model.LeagueDetail
import br.com.neresfelip.herofitbrasil.presentation.model.LeagueDetailUI

object LeagueDetailMapper {

    fun LeagueDetailResponse.toModel(): LeagueDetail {
        val league = leagues.firstOrNull()
            ?: throw IllegalStateException("Nenhuma liga encontrada")

        return LeagueDetail(
            id = league.id,
            name = league.name,
            sport = league.sport,
            country = league.country,
            urlInstagram = league.instagram?.let { "https://$it" },
            urlFacebook = league.facebook?.let { "https://$it" },
            urlYoutube = league.youtube?.let { "https://$it" },
            description = league.descriptionEN,
            urlLogo = league.logo,
            urlBanner = league.banner
        )
    }

    fun LeagueDetail.toUI(): LeagueDetailUI {
        return LeagueDetailUI(
            id = id,
            name = name,
            sport = sport,
            country = country,
            urlInstagram = urlInstagram,
            urlFacebook = urlFacebook,
            urlYoutube = urlYoutube,
            description = description,
            urlLogo = urlLogo,
            urlBanner = urlBanner
        )
    }

}