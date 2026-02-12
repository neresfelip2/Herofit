package br.com.neresfelip.herofitbrasil.data.remote.response

import com.google.gson.annotations.SerializedName

data class AllSportsResponse(
    @SerializedName("sports")
    val sports: List<SportResponse>
)

data class SportResponse(
    @SerializedName("idSport")
    val idSport: Int,

    @SerializedName("strSport")
    val strSport: String,

    @SerializedName("strFormat")
    val strFormat: String,

    @SerializedName("strSportThumb")
    val strSportThumb: String,

    @SerializedName("strSportThumbBW")
    val strSportThumbBW: String,

    @SerializedName("strSportIconGreen")
    val strSportIconGreen: String,

    @SerializedName("strSportDescription")
    val strSportDescription: String,
)