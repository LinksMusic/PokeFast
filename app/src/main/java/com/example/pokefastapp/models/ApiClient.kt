package com.example.pokefastapp.models

import com.google.gson.Gson
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
     * Führt GET Anfrage aus und empfängt eine Nachricht im JSON Format
     * @param type Welche GET Anfrage soll ausgeführt werden
     * @param message Welches Set/Karte soll gesucht werden. Leere Message für alle
     */
    fun sendGet(type: String, message: String = "") {
        thread {
            val url: URL
            when (type) {
                "getSet" -> url = URL("https://api.pokemontcg.io/v2/sets/$message")
                "getCard" -> url = URL("https://api.pokemontcg.io/v2/cards/$message")
                else -> {
                    url = URL("")
                    throw IllegalArgumentException("Invalid data type: $type")
                }
            }
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"  // optional default is GET
                setRequestProperty("X-Api-Key", "4e64dce7-86e2-4d88-b4f9-6ca84e4179e2")

                InputStreamReader(inputStream).use { reader ->
                    val json = reader.readText()
                    if (type.startsWith("getSet")) {
                        if (message.equals("")){
                            val setResponse = parseMultipleSetsJson(json)
                            println("Received set objects:")
                            for (item in setResponse.dataList) {
                                println(item.id)
                            }
                        }
                        else{
                            val setResponse = parseSetJson(json)
                            println("Received set object:")
                            println(setResponse.data.id)
                        }

                    } else if (type.startsWith("getCard")) {
                        val cardResponse = parseCardJson(json)
                        println("Received card object:")
                        println(cardResponse.data.id)
                    } else {
                        throw IllegalArgumentException("Invalid data type: $type")
                    }
                }
            }
        }
    }


    fun parseSetJson(json: String): PokemonSet {
        val gson = Gson()
        return gson.fromJson(json, PokemonSet::class.java)
    }

    fun parseMultipleSetsJson(json: String): PokemonMultipleSets {
        val gson = Gson()
        return gson.fromJson(json, PokemonMultipleSets::class.java)
    }

    fun parseCardJson(json: String): PokemonCard {
        val gson = Gson()
        return gson.fromJson(json, PokemonCard::class.java)
    }
}