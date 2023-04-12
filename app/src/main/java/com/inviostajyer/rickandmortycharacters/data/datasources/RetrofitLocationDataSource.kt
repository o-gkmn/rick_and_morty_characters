package com.inviostajyer.rickandmortycharacters.data.datasources

import com.inviostajyer.rickandmortycharacters.data.interfaces.RickAndMortyApi
import com.inviostajyer.rickandmortycharacters.data.interfaces.RickAndMortyDatasource
import com.inviostajyer.rickandmortycharacters.domain.model.Character
import com.inviostajyer.rickandmortycharacters.domain.model.Location
import javax.inject.Inject

class RetrofitLocationDataSource @Inject constructor(private val rickAndMortyApi: RickAndMortyApi) : RickAndMortyDatasource {
    override suspend fun getAllLocations(): List<Location> {
        val locationList = rickAndMortyApi.getAllLocations().execute()

        if (locationList.isSuccessful) {
            return locationList.body()!!.locationList
        } else {
            throw Exception("Bir sorun oluştu")
        }
    }

    override suspend fun getLocation(id: Int): Location {
        val location = rickAndMortyApi.getLocation(id).execute()
        if (location.isSuccessful) {
            return location.body()!!
        } else {
            throw Exception("Bir sorun oluştu")
        }
    }

    override suspend fun getAllCharactersByLocation(characterIds: List<Int>): List<Character> {
        val characterList = rickAndMortyApi.getAllCharactersByLocation(characterIds).execute()

        if(characterList.isSuccessful){
            return characterList.body()!!
        }else{
            throw Exception("Bir sorun oluştu")
        }
    }

    override suspend fun getCharacters(id: Int): Character {
        val character = rickAndMortyApi.getCharacter(id).execute()
        if (character.isSuccessful) {
            return character.body()!!
        } else {
            throw Exception("Bir sorun oluştu")
        }
    }
}