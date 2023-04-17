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
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) : ViewModel() {
    var selectedChip by mutableStateOf(Location.emptyLocation())
    var characterList by mutableStateOf(listOf<Character>())

    lateinit var locationList: List<Location>

    var showDialog by mutableStateOf(false)
    lateinit var exceptionText: String

    fun getAllLocation() {
        try {
            locationList = runBlocking { withContext(Dispatchers.IO) { rickAndMortyRepository.getAllLocations() } }
        } catch (e: IOException) {
            showDialog = true
            locationList = arrayListOf()
            exceptionText = "İnternet bağlantısı kurulamadı"
        } catch (e: HttpException) {
            showDialog = true
            locationList = arrayListOf()
            exceptionText = "Uzak sunucuyla bağlantı kurulamadı"
        } catch (e: Exception) {
            showDialog = true
            locationList = arrayListOf()
            exceptionText = "Bir şeyler yanlış gitti"
        }
    }

    fun getCharactersByLocation() {
        try {
            val characterIds = arrayListOf<Int>()
            selectedChip.residents.forEach {
                characterIds.add(it.drop(42).toInt())
            }
            if (characterIds.isEmpty()) {
                characterList = arrayListOf()
            } else {
                characterList = runBlocking {
                    withContext(Dispatchers.IO) {
                        rickAndMortyRepository.getAllCharactersByLocation(characterIds)
                    }
                }
            }
        } catch (e: IOException) {
            showDialog = true
            locationList = arrayListOf()
            exceptionText = "İnternet bağlantısı kurulamadı"
        } catch (e: HttpException) {
            showDialog = true
            locationList = arrayListOf()
            exceptionText = "Uzak sunucuyla bağlantı kurulamadı"
        } catch (e: Exception) {
            showDialog = true
            locationList = arrayListOf()
            exceptionText = "Bir şeyler yanlış gitti"
        }
    }
}