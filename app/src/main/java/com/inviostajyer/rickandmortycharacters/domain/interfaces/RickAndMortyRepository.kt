package com.inviostajyer.rickandmortycharacters.domain.interfaces

import androidx.paging.PagingData
import com.inviostajyer.rickandmortycharacters.domain.model.Character
import com.inviostajyer.rickandmortycharacters.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {
    fun getAllLocations() : Flow<PagingData<Location>>
    suspend fun getLocation(id : Int) : Location
    suspend fun getAllCharactersByLocation(characterIds: List<Int>) : List<Character>
    suspend fun getCharacters(id : Int) : Character
}