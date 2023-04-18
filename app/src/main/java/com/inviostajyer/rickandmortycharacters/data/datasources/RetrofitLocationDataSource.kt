package com.inviostajyer.rickandmortycharacters.data.datasources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.inviostajyer.rickandmortycharacters.core.pagination.DefaultPager
import com.inviostajyer.rickandmortycharacters.data.interfaces.RickAndMortyApi
import com.inviostajyer.rickandmortycharacters.data.interfaces.RickAndMortyDatasource
import com.inviostajyer.rickandmortycharacters.domain.model.Character
import com.inviostajyer.rickandmortycharacters.domain.model.Location
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RetrofitLocationDataSource @Inject constructor(private val rickAndMortyApi: RickAndMortyApi) :
    RickAndMortyDatasource {
    override fun getAllLocations(): Flow<PagingData<Location>> {
        return Pager(PagingConfig(pageSize = 19)) {
            DefaultPager(rickAndMortyApi)
        }.flow
    }


    override suspend fun getLocation(id: Int): Location {
        try {
            val location = rickAndMortyApi.getLocation(id).execute()

            if (location.isSuccessful) {
                return location.body()!!
            }
        } catch (e: IOException) {
            throw e
        } catch (e: HttpException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
        return Location.emptyLocation()
    }

    override suspend fun getAllCharactersByLocation(characterIds: List<Int>): List<Character> {
        try {
            val characterList = rickAndMortyApi.getAllCharactersByLocation(characterIds).execute()

            if (characterList.isSuccessful) {
                return characterList.body()!!
            }
        } catch (e: IOException) {
            throw e
        } catch (e: HttpException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
        return arrayListOf()
    }

    override suspend fun getCharacters(id: Int): Character {
        try {
            val character = rickAndMortyApi.getCharacter(id).execute()
            if (character.isSuccessful) {
                return character.body()!!
            }
        } catch (e: IOException) {
            throw e
        } catch (e: HttpException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
        return Character.emptyCharacter()
    }
}