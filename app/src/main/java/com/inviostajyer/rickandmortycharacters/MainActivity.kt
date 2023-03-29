package com.inviostajyer.rickandmortycharacters

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.inviostajyer.rickandmortycharacters.ui.theme.RickAndMortyCharactersTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = getSharedPreferences("com.inviostajyer.rickandmortycharacters", Context.MODE_PRIVATE)

        setContent {
            RickAndMortyCharactersTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavigateHost(preferences)
                }
            }
        }
    }
}