package com.inviostajyer.rickandmortycharacters.domain.repository

import androidx.paging.PagingData
import com.inviostajyer.rickandmortycharacters.data.interfaces.RickAndMortyDatasource
import com.inviostajyer.rickandmortycharacters.domain.interfaces.RickAndMortyRepository
import com.inviostajyer.rickandmortycharacters.domain.model.Character
import com.inviostajyer.rickandmortycharacters.domain.model.Location
import kotlinx.coroutines.flow.Flow

class RickAndMortyRepositoryImpl(private val rickAndMortyDataSource: RickAndMortyDatasource) : RickAndMortyRepository {
    override fun getAllLocations(): Flow<PagingData<Location>> {
        return rickAndMortyDataSource.getAllLocations()
    }

    override suspend fun getLocation(id : Int): Location {
        return rickAndMortyDataSource.getLocation(id)
    }

    override suspend fun getAllCharactersByLocation(characterIds: List<Int>): List<Character> {
        return rickAndMortyDataSource.getAllCharactersByLocation(characterIds)
    }

    override suspend fun getCharacters(id: Int): Character {
        return rickAndMortyDataSource.getCharacters(id)
    }
}