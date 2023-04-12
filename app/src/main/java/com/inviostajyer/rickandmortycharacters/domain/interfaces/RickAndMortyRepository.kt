package com.inviostajyer.rickandmortycharacters.domain.interfaces

import com.inviostajyer.rickandmortycharacters.domain.model.Character
import com.inviostajyer.rickandmortycharacters.domain.model.Location

interface RickAndMortyRepository {
    suspend fun getAllLocations() : List<Location>
    suspend fun getLocation(id : Int) : Location
    suspend fun getAllCharactersByLocation(characterIds: List<Int>) : List<Character>
    suspend fun getCharacters(id : Int) : Character
}