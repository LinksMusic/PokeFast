package com.example.pokefastapp.models

import com.google.gson.annotations.SerializedName

data class PokemonMultipleSets(
    @SerializedName("data")
    val dataList: List<Set>,
    val q: String?,
    val page: Int?,
    val pageSize: Int?,
    val orderBy: String?,
    val select: String?
)

data class PokemonSet(
    val data: Set
)

data class Set(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("series")
    val series: String,
    @SerializedName("printedTotal")
    val printedTotal: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("legalities")
    val legalities: Legalities?,
    @SerializedName("ptcgoCode")
    val ptcgoCode: String,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("images")
    val images: SetImages?
)

data class SetImages(
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("logo")
    val logo: String
)
