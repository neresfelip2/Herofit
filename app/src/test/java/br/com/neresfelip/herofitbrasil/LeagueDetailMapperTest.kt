package br.com.neresfelip.herofitbrasil

import br.com.neresfelip.herofitbrasil.data.remote.response.LeagueDetailEntity
import br.com.neresfelip.herofitbrasil.data.remote.response.LeagueDetailResponse
import br.com.neresfelip.herofitbrasil.data.mapper.LeagueDetailMapper
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class LeagueDetailMapperTest {

    @Test
    fun `toModel correctly maps LeagueDetailResponse to LeagueDetail`() {

        val response = LeagueDetailResponse(
            leagues = listOf(
                LeagueDetailEntity(
                    id = 1,
                    soccerXmlId = null,
                    apiFootballId = null,
                    sport = "Soccer",
                    name = "Premier League",
                    alternateNames = null,
                    division = null,
                    cupId = null,
                    currentSeason = "2024",
                    formedYear = "1992",
                    firstEventDate = null,
                    gender = "Male",
                    country = "England",
                    website = null,
                    facebook = "www.facebook.com/premierleague",
                    instagram = "www.instagram.com/premierleague",
                    twitter = null,
                    youtube = "www.youtube.com/premierleague",
                    rss = null,
                    descriptionEN = "English Premier League",
                    descriptionDE = null,
                    descriptionFR = null,
                    descriptionIT = null,
                    descriptionCN = null,
                    descriptionJP = null,
                    descriptionRU = null,
                    descriptionES = null,
                    descriptionPT = null,
                    descriptionSE = null,
                    descriptionNL = null,
                    descriptionHU = null,
                    descriptionNO = null,
                    descriptionPL = null,
                    descriptionIL = null,
                    tvRights = null,
                    fanart1 = null,
                    fanart2 = null,
                    fanart3 = null,
                    fanart4 = null,
                    banner = "https://example.com/banner.png",
                    badge = null,
                    logo = "https://example.com/logo.png",
                    poster = null,
                    trophy = null,
                    namingFormat = null,
                    isComplete = null,
                    locked = null
                )
            )
        )

        val result = with(LeagueDetailMapper) { response.toModel() }

        assertEquals(1, result.id)
        assertEquals("Premier League", result.name)
        assertEquals("Soccer", result.sport)
        assertEquals("England", result.country)
        assertEquals("https://www.facebook.com/premierleague", result.urlFacebook)
        assertEquals("https://www.instagram.com/premierleague", result.urlInstagram)
        assertEquals("https://www.youtube.com/premierleague", result.urlYoutube)
        assertEquals("English Premier League", result.description)
        assertEquals("https://example.com/logo.png", result.urlLogo)
        assertEquals("https://example.com/banner.png", result.urlBanner)
    }

    @Test(expected = IllegalStateException::class)
    fun `toModel throws exception when leagues list is empty`() {

        val response = LeagueDetailResponse(leagues = emptyList())

        with(LeagueDetailMapper) { response.toModel() }

    }

    @Test
    fun `toModel handles null social media URLs correctly`() {

        val response = LeagueDetailResponse(
            leagues = listOf(
                LeagueDetailEntity(
                    id = 1,
                    soccerXmlId = null,
                    apiFootballId = null,
                    sport = "Soccer",
                    name = "Premier League",
                    alternateNames = null,
                    division = null,
                    cupId = null,
                    currentSeason = null,
                    formedYear = null,
                    firstEventDate = null,
                    gender = null,
                    country = "England",
                    website = null,
                    facebook = null,
                    instagram = null,
                    twitter = null,
                    youtube = null,
                    rss = null,
                    descriptionEN = "English Premier League",
                    descriptionDE = null,
                    descriptionFR = null,
                    descriptionIT = null,
                    descriptionCN = null,
                    descriptionJP = null,
                    descriptionRU = null,
                    descriptionES = null,
                    descriptionPT = null,
                    descriptionSE = null,
                    descriptionNL = null,
                    descriptionHU = null,
                    descriptionNO = null,
                    descriptionPL = null,
                    descriptionIL = null,
                    tvRights = null,
                    fanart1 = null,
                    fanart2 = null,
                    fanart3 = null,
                    fanart4 = null,
                    banner = null,
                    badge = null,
                    logo = null,
                    poster = null,
                    trophy = null,
                    namingFormat = null,
                    isComplete = null,
                    locked = null
                )
            )
        )


        val result = with(LeagueDetailMapper) { response.toModel() }

        assertNull(result.urlFacebook)
        assertNull(result.urlInstagram)
        assertNull(result.urlYoutube)
    }

}