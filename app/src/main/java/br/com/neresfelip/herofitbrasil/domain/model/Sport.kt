package br.com.neresfelip.herofitbrasil.domain.model

data class Sport(
    val idSport: Int,
    val name: String,
    val format: String,
    val thumb: String,
    val thumbBW: String,
    val iconGreen: String,
    val description: String,
)
