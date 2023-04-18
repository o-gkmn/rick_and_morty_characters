package com.inviostajyer.rickandmortycharacters.data.interfaces

import androidx.paging.PagingData
import com.inviostajyer.rickandmortycharacters.domain.model.Character
import com.inviostajyer.rickandmortycharacters.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface RickAndMortyDatasource {
    fun getAllLocations() : Flow<PagingData<Location>>
    suspend fun getLocation(id : Int) : Location
    suspend fun getAllCharactersByLocation(characterIds : List<Int>) : List<Character>
    suspend fun getCharacters(id : Int) : Character

}