package br.com.neresfelip.herofitbrasil.domain.model

data class League(
    val id: Int,
    val name: String,
    val sport: String,
    val country: String,
    val urlLogo: String?
)
