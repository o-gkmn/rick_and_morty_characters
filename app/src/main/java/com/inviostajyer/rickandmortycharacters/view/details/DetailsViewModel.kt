package com.inviostajyer.rickandmortycharacters.view.details

import androidx.lifecycle.ViewModel
import com.inviostajyer.rickandmortycharacters.domain.interfaces.RickAndMortyRepository
import com.inviostajyer.rickandmortycharacters.domain.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailsViewModel @Inject constructor(rickAndMortyRepository: RickAndMortyRepository, val id: Int) : ViewModel() {

    var selectedCharacter: Character

    init {
        selectedCharacter = runBlocking { withContext(Dispatchers.IO) { rickAndMortyRepository.getCharacters(id) } }
    }
}