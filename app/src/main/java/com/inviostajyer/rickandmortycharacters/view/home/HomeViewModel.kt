package com.inviostajyer.rickandmortycharacters.view.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.inviostajyer.rickandmortycharacters.domain.interfaces.RickAndMortyRepository
import com.inviostajyer.rickandmortycharacters.domain.model.Character
import com.inviostajyer.rickandmortycharacters.domain.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) : ViewModel() {
    val emptyLocation = Location(-1, "", "", "", ArrayList(), "", "")
    var selectedChip by mutableStateOf(emptyLocation)
    var characterList by mutableStateOf(listOf<Character>())

    lateinit var locationList: List<Location>

    fun getAllLocation() {
        locationList = runBlocking { withContext(Dispatchers.IO) { rickAndMortyRepository.getAllLocations() } }
    }

    fun getCharactersByLocation() {
        if (selectedChip.id != -1) {
            val characterIds = arrayListOf<Int>()
            selectedChip.residents.forEach {
                characterIds.add(it.drop(42).toInt())
            }
            if(characterIds.isEmpty()){
                characterList = arrayListOf()
            }else{
                characterList = runBlocking { withContext(Dispatchers.IO) { rickAndMortyRepository.getAllCharactersByLocation(characterIds) } }
            }
        }else{
            characterList = arrayListOf()
        }
    }
}