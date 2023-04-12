package com.inviostajyer.rickandmortycharacters.data.interfaces

import com.inviostajyer.rickandmortycharacters.domain.model.Character
import com.inviostajyer.rickandmortycharacters.domain.model.Location

interface RickAndMortyDatasource {
    suspend fun getAllLocations() : List<Location>
    suspend fun getLocation(id : Int) : Location
    suspend fun getAllCharactersByLocation(characterIds : List<Int>) : List<Character>
    suspend fun getCharacters(id : Int) : Character

}