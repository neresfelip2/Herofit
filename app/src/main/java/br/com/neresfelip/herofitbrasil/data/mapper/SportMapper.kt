package br.com.neresfelip.herofitbrasil.data.mapper

import br.com.neresfelip.herofitbrasil.data.remote.response.SportResponse
import br.com.neresfelip.herofitbrasil.domain.model.Sport
import br.com.neresfelip.herofitbrasil.presentation.model.SportUI

object SportMapper {

    fun SportResponse.toDomain() : Sport {
        return Sport(
            idSport = idSport,
            name = strSport,
            format = strFormat,
            thumb = strSportThumb,
            thumbBW = strSportThumbBW,
            iconGreen = strSportIconGreen,
            description = strSportDescription
        )
    }

    fun List<Sport>.toUI(): List<SportUI> {
        return map {
            SportUI(
                id = it.idSport,
                name = it.name,
                urlImage = it.thumb
            )
        }
    }

}