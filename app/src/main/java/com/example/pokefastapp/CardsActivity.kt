package com.example.pokefastapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pokefastapp.cardbutton.CardButton
import com.example.pokefastapp.models.ApiClient
import com.example.pokefastapp.models.PokemonCard
import com.example.pokefastapp.models.PokemonMultipleCards
import com.example.pokefastapp.models.PokemonMultipleSets
import com.example.pokefastapp.models.PokemonSet
import com.example.pokefastapp.setbuttongridview.SetButtonGridView
import com.example.pokefastapp.setbuttonlistview.SetButtonListView
import kotlinx.coroutines.runBlocking

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val setId = intent.getStringExtra("set_id")

        if (setId != null) {
            runBlocking {
                cardView(setId)
            }
        }
    }

    suspend fun cardView(setId: String) {
        val apiClient = ApiClient()
        val cardResponse = apiClient.sendGet("getCardsOfSet", "?q=set.id:$setId")
        val cardDataList: MutableList<PokemonCard> = mutableListOf()
        if (cardResponse is PokemonMultipleCards) {
            for (item in cardResponse.dataList) {
                cardDataList.add(item)
            }
        }

        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            val chunkedDataList = cardDataList.chunked(3)

                            chunkedDataList.forEach { chunk ->
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    chunk.forEachIndexed { index, cardData ->
                                        CardButton(
                                            cardImage = rememberAsyncImagePainter(cardData.images.large),
                                            cardText = cardData.name,
                                            onButtonTapped = {
                                                Toast.makeText(
                                                    applicationContext,
                                                    "Hello World!",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            },
                                            modifier = Modifier.weight(1f) // Added weight modifier
                                        )

                                        if (index < chunk.lastIndex) {
                                            Spacer(modifier = Modifier.width(10.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}