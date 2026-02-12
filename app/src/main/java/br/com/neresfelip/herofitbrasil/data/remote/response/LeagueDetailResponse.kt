package br.com.neresfelip.herofitbrasil.data.remote.response

import com.google.gson.annotations.SerializedName

data class LeagueDetailResponse(
    @SerializedName("leagues")
    val leagues: List<LeagueDetailEntity>
)

data class LeagueDetailEntity(
    @SerializedName("idLeague")
    val id: Int,

    @SerializedName("idSoccerXML")
    val soccerXmlId: String?,

    @SerializedName("idAPIfootball")
    val apiFootballId: String?,

    @SerializedName("strSport")
    val sport: String,

    @SerializedName("strLeague")
    val name: String,

    @SerializedName("strLeagueAlternate")
    val alternateNames: String?,

    @SerializedName("intDivision")
    val division: String?,

    @SerializedName("idCup")
    val cupId: String?,

    @SerializedName("strCurrentSeason")
    val currentSeason: String?,

    @SerializedName("intFormedYear")
    val formedYear: String?,

    @SerializedName("dateFirstEvent")
    val firstEventDate: String?,

    @SerializedName("strGender")
    val gender: String?,

    @SerializedName("strCountry")
    val country: String?,

    @SerializedName("strWebsite")
    val website: String?,

    @SerializedName("strFacebook")
    val facebook: String?,

    @SerializedName("strInstagram")
    val instagram: String?,

    @SerializedName("strTwitter")
    val twitter: String?,

    @SerializedName("strYoutube")
    val youtube: String?,

    @SerializedName("strRSS")
    val rss: String?,

    @SerializedName("strDescriptionEN")
    val descriptionEN: String?,

    @SerializedName("strDescriptionDE")
    val descriptionDE: String?,

    @SerializedName("strDescriptionFR")
    val descriptionFR: String?,

    @SerializedName("strDescriptionIT")
    val descriptionIT: String?,

    @SerializedName("strDescriptionCN")
    val descriptionCN: String?,

    @SerializedName("strDescriptionJP")
    val descriptionJP: String?,

    @SerializedName("strDescriptionRU")
    val descriptionRU: String?,

    @SerializedName("strDescriptionES")
    val descriptionES: String?,

    @SerializedName("strDescriptionPT")
    val descriptionPT: String?,

    @SerializedName("strDescriptionSE")
    val descriptionSE: String?,

    @SerializedName("strDescriptionNL")
    val descriptionNL: String?,

    @SerializedName("strDescriptionHU")
    val descriptionHU: String?,

    @SerializedName("strDescriptionNO")
    val descriptionNO: String?,

    @SerializedName("strDescriptionPL")
    val descriptionPL: String?,

    @SerializedName("strDescriptionIL")
    val descriptionIL: String?,

    @SerializedName("strTvRights")
    val tvRights: String?,

    @SerializedName("strFanart1")
    val fanart1: String?,

    @SerializedName("strFanart2")
    val fanart2: String?,

    @SerializedName("strFanart3")
    val fanart3: String?,

    @SerializedName("strFanart4")
    val fanart4: String?,

    @SerializedName("strBanner")
    val banner: String?,

    @SerializedName("strBadge")
    val badge: String?,

    @SerializedName("strLogo")
    val logo: String?,

    @SerializedName("strPoster")
    val poster: String?,

    @SerializedName("strTrophy")
    val trophy: String?,

    @SerializedName("strNaming")
    val namingFormat: String?,

    @SerializedName("strComplete")
    val isComplete: String?,

    @SerializedName("strLocked")
    val locked: String?
)
