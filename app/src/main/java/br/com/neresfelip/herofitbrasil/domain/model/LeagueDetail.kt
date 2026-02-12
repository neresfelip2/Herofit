package br.com.neresfelip.herofitbrasil.domain.model

data class LeagueDetail(
    val id: Int,
    val name: String,
    val sport: String,
    val country: String?,
    val urlInstagram: String?,
    val urlFacebook: String?,
    val urlYoutube: String?,
    val description: String?,
    val urlLogo: String?,
    val urlBanner: String?
)
