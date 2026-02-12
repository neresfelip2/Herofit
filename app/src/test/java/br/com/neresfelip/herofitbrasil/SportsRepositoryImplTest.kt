package br.com.neresfelip.herofitbrasil

import br.com.neresfelip.herofitbrasil.data.remote.SportAPI
import br.com.neresfelip.herofitbrasil.data.remote.response.AllSportsResponse
import br.com.neresfelip.herofitbrasil.data.remote.response.LeagueListResponse
import br.com.neresfelip.herofitbrasil.data.remote.response.LeagueResponse
import br.com.neresfelip.herofitbrasil.data.remote.response.SportResponse
import br.com.neresfelip.herofitbrasil.data.repository.SportsRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class SportsRepositoryImplTest {

    private lateinit var repository: SportsRepositoryImpl
    private lateinit var api: SportAPI

    @Before
    fun setup() {
        api = mockk()
        repository = SportsRepositoryImpl(api)
    }

    @Test
    fun `getAllSports returns success when API call succeeds`() = runTest {

        val mockResponse = AllSportsResponse(
            sports = listOf(
                SportResponse(
                    idSport = 1,
                    strSport = "Soccer",
                    strFormat = "TeamvsTeam",
                    strSportThumb = "https://example.com/soccer.jpg",
                    strSportThumbBW = "https://example.com/soccer_bw.jpg",
                    strSportIconGreen = "https://example.com/soccer_icon.jpg",
                    strSportDescription = "Soccer description"
                )
            )
        )
        coEvery { api.getAllSports() } returns mockResponse

        val result = repository.getAllSports()

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        assertEquals("Soccer", result.getOrNull()?.first()?.name)
    }

    @Test
    fun `getAllSports returns failure with network error message when no internet`() = runTest {

        coEvery { api.getAllSports() } throws UnknownHostException()

        val result = repository.getAllSports()

        assertTrue(result.isFailure)
        assertEquals("Sem conexão com a internet", result.exceptionOrNull()?.message)
    }

    @Test
    fun `getLeaguesBySportName returns success when API call succeeds`() = runTest {

        val mockResponse = LeagueListResponse(
            leagues = listOf(
                LeagueResponse(
                    id = 1,
                    soccerXmlId = null,
                    apiFootballId = null,
                    sport = "Soccer",
                    name = "Premier League",
                    alternativeName = null,
                    division = null,
                    cupId = null,
                    currentSeason = "2024",
                    formedYear = "1992",
                    firstEventDate = null,
                    gender = "Male",
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
                    logo = "https://example.com/logo.png",
                    poster = null,
                    trophy = null,
                    namingFormat = null,
                    isComplete = null,
                    locked = null
                )
            )
        )
        coEvery { api.getLeaguesBySportName("Soccer") } returns mockResponse

        val result = repository.getLeaguesBySportName("Soccer")

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        assertEquals("Premier League", result.getOrNull()?.first()?.name)
        assertEquals("England", result.getOrNull()?.first()?.country)
    }

    @Test
    fun `getLeaguesBySportName returns failure when API call fails`() = runTest {

        coEvery { api.getLeaguesBySportName("Soccer") } throws Exception("API Error")

        val result = repository.getLeaguesBySportName("Soccer")

        assertTrue(result.isFailure)
        assertNotNull(result.exceptionOrNull()?.message)
    }

}