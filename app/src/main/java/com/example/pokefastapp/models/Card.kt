package com.example.pokefastapp.models

import com.google.gson.annotations.SerializedName


data class PokemonMultipleCards(
    @SerializedName("data")
    val dataList: List<PokemonCard>
)
data class SingleCard(
    val data: PokemonCard
)

data class PokemonCard(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("supertype")
    val supertype: String,
    @SerializedName("subtype")
    val subtype: List<String>,
    @SerializedName("level")
    val level: String,
    @SerializedName("hp")
    val hp: String,
    @SerializedName("types")
    val types: List<String>,
    @SerializedName("evolvesFrom")
    val evolvesFrom: String,
    @SerializedName("evolvesTo")
    val evolvesTo: List<String>,
    @SerializedName("rules")
    val rules: List<String>,
    @SerializedName("ancientTrait")
    val ancientTrait: AncientTrait,
    @SerializedName("abilities")
    val abilities: List<Abilities>,
    @SerializedName("attacks")
    val attacks: List<Attacks>,
    @SerializedName("weaknesses")
    val weaknesses: List<Weaknesses>,
    @SerializedName("resistances")
    val resistances: List<Resistances>,
    @SerializedName("retreatCost")
    val retreatCost: List<String>,
    @SerializedName("convertedRetreatCost")
    val convertedRetreatCost: Int,
    @SerializedName("set")
    val set: PokemonSet,
    @SerializedName("number")
    val number: String,
    @SerializedName("artist")
    val artist: String,
    @SerializedName("rarity")
    val rarity: String,
    @SerializedName("flavorText")
    val flavorText: String,
    @SerializedName("nationalPokedexNumbers")
    val nationalPokedexNumbers: List<Int>,
    @SerializedName("legalities")
    val legalities: Legalities,
    @SerializedName("regulationMark")
    val regulationMark: String,
    @SerializedName("images")
    val images: CardImages,
    @SerializedName("tcgplayer")
    val tcgplayer: Tcgplayer,
    @SerializedName("cardmarket")
    val cardmarket: Cardmarket
)
data class AncientTrait(
    @SerializedName("name")
    val name: String,
    @SerializedName("text")
    val text: String
)

data class Abilities(
    @SerializedName("name")
    val name: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("type")
    val type: String
)

data class Attacks(
    @SerializedName("cost")
    val cost: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("damage")
    val damage: String,
    @SerializedName("convertedEnergyCost")
    val convertedEnergyCost: Int
)

data class Weaknesses(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String
)

data class Resistances(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String
)

data class Legalities(
    @SerializedName("standard")
    val standard: String,
    @SerializedName("expanded")
    val expanded: String,
    @SerializedName("unlimited")
    val unlimited: String
)

data class CardImages(
    @SerializedName("small")
    val small: String,
    @SerializedName("large")
    val large: String
)

data class Tcgplayer(
    @SerializedName("url")
    val url: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("prices")
    val prices: PriceUS
)

data class Cardmarket(
    @SerializedName("url")
    val url: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("prices")
    val prices: PriceEU
)

data class PriceUS(
    @SerializedName("low")
    val low: Double,
    @SerializedName("mid")
    val mid: Double,
    @SerializedName("high")
    val high: Double,
    @SerializedName("market")
    val market: Double,
    @SerializedName("directLow")
    val directLow: Double
)

data class PriceEU(
    @SerializedName("averageSellPrice")
    val averageSellPrice: Double,
    @SerializedName("lowPrice")
    val lowPrice: Double,
    @SerializedName("trendPrice")
    val trendPrice: Double,
    @SerializedName("germanProLow")
    val germanProLow: Double,
    @SerializedName("suggestedPrice")
    val suggestedPrice: Double,
    @SerializedName("reverseHoloSell")
    val reverseHoloSell: Double,
    @SerializedName("reverseHoloLow")
    val reverseHoloLow: Double,
    @SerializedName("reverseHoloTrend")
    val reverseHoloTrend: Double,
    @SerializedName("lowPriceExPlus")
    val lowPriceExPlus: Double,
    @SerializedName("avg1")
    val avg1: Double,
    @SerializedName("avg7")
    val avg7: Double,
    @SerializedName("avg30")
    val avg30: Double,
    @SerializedName("reverseHoloAvg1")
    val reverseHoloAvg1: Double,
    @SerializedName("reverseHoloAvg7")
    val reverseHoloAvg7: Double,
    @SerializedName("reverseHoloAvg30")
    val reverseHoloAvg30: Double
)