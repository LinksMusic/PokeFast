package com.example.pokefastapp.models

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

/**
 * Erstellt Clienten und führt Anfragen an die API aus
 */
class ApiClient {
    //API Key: 4e64dce7-86e2-4d88-b4f9-6ca84e4179e2
    /**
     * Sends a GET request and receives a message in JSON format
     * @param type Which GET request to execute
     * @param message Which set/card to search for. Empty message for all
     * @return The parsed response object based on the type of request
     */
    suspend fun sendGet(type: String, message: String = ""): Any? {
        return withContext(Dispatchers.IO) {
            return@withContext when (type) {
                "getSet" -> {
                    val url = URL("https://api.pokemontcg.io/v2/sets/$message")
                    sendRequest(url) { json ->
                        if (message.isBlank()) {
                            parseMultipleSetsJson(json)
                        } else {
                            parseSetJson(json)
                        }
                    }
                }
                "getCard" -> {
                    val url = URL("https://api.pokemontcg.io/v2/cards$message")
                    sendRequest(url) { json ->
                        parseCardJson(json)
                    }
                }
                "getCardsOfSet" ->{
                    val url = URL("https://api.pokemontcg.io/v2/cards$message")
                    sendRequest(url) { json ->
                        parseCardsOfSetJson(json)
                    }
                }
                else -> throw IllegalArgumentException("Invalid data type: $type")
            }
        }
    }

    private inline fun <reified T> sendRequest(url: URL, parseJson: (String) -> T): T {
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("X-Api-Key", "4e64dce7-86e2-4d88-b4f9-6ca84e4179e2")

        InputStreamReader(connection.inputStream).use { reader ->
            val json = reader.readText()
            return parseJson(json)
        }
    }


    fun parseSetJson(json: String): SingleSet {
        val gson = Gson()
        return gson.fromJson(json, SingleSet::class.java)
    }

    fun parseMultipleSetsJson(json: String): PokemonMultipleSets {
        val gson = Gson()
        return gson.fromJson(json, PokemonMultipleSets::class.java)
    }

    fun parseCardJson(json: String): SingleCard {
        val gson = Gson()
        return gson.fromJson(json, SingleCard::class.java)
    }

    fun parseCardsOfSetJson(json: String): PokemonMultipleCards {
        val gson = Gson()
        return gson.fromJson(json, PokemonMultipleCards::class.java)
    }
}