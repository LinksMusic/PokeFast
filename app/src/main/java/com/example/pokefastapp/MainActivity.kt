package com.example.pokefastapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.pokefastapp.models.ApiClient
import com.example.pokefastapp.models.PokemonMultipleSets
import com.example.pokefastapp.models.PokemonSet
import com.example.pokefastapp.setbuttongridview.SetButtonGridView
import com.example.pokefastapp.setbuttonlistview.SetButtonListView
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)

        runBlocking{
            listView()
        }

        //setContentView(R.layout.activity_main)
    }

    suspend fun listView(){
        val setDataList = getMultipleSets()

        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            setDataList.forEachIndexed { index, setData ->
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    SetButtonListView(
                                        setLogo = rememberAsyncImagePainter(setData.images.symbol),
                                        setName = rememberAsyncImagePainter(setData.images.logo),
                                        onButtonTapped = {
                                            Toast.makeText(
                                                applicationContext,
                                                "Hello World!",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    )
                                }

                                // Add a spacer between rows
                                if (index < setDataList.lastIndex) {
                                    Spacer(modifier = Modifier.height(40.dp))
                                }
                            }
                        }
                    }
                }
            }
        }


    }

    suspend fun getMultipleSets(): MutableList<PokemonSet>{
        val apiClient = ApiClient()
        val multipleSetsResponse = apiClient.sendGet("getSet")
        val setDataList: MutableList<PokemonSet> = mutableListOf()
        if (multipleSetsResponse is PokemonMultipleSets) {
            for (item in multipleSetsResponse.dataList){
                setDataList.add(item)
            }
        }
        return setDataList
    }

    suspend fun gridView(){
        val setDataList = getMultipleSets()

        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            val chunkedDataList = setDataList.chunked(3)

                            chunkedDataList.forEach { chunk ->
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    chunk.forEachIndexed { index, setData ->
                                        SetButtonGridView(
                                            setlogo = rememberAsyncImagePainter(setData.images.symbol),
                                            setNameLabel = setData.name,
                                            onButtonTapped = {
                                                Toast.makeText(
                                                    applicationContext,
                                                    "Hello World!",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        )

                                        if (index < chunk.lastIndex) {
                                            Spacer(modifier = Modifier.width(60.dp))
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