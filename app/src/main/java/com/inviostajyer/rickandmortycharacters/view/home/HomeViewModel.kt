package com.inviostajyer.rickandmortycharacters.view.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.inviostajyer.rickandmortycharacters.domain.interfaces.RickAndMortyRepository
import com.inviostajyer.rickandmortycharacters.domain.model.Character
import com.inviostajyer.rickandmortycharacters.domain.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository,
) : ViewModel() {

    var selectedChip by mutableStateOf(Location.emptyLocation())
    var characterList by mutableStateOf(listOf<Character>())

    val locations: Flow<PagingData<Location>> = rickAndMortyRepository.getAllLocations().cachedIn(viewModelScope)

    var showDialog by mutableStateOf(false)
    lateinit var exceptionText: String

    fun getCharactersByLocation() {
        try {
            val characterIds = arrayListOf<Int>()
            selectedChip.residents.forEach {
                characterIds.add(it.drop(42).toInt())
            }
            characterList = if (characterIds.isEmpty()) {
                arrayListOf()
            } else {
                runBlocking {
                    withContext(Dispatchers.IO) {
                        rickAndMortyRepository.getAllCharactersByLocation(characterIds)
                    }
                }
            }
        } catch (e: IOException) {
            showDialog = true
            exceptionText = "İnternet bağlantısı kurulamadı"
        } catch (e: HttpException) {
            showDialog = true
            exceptionText = "Uzak sunucuyla bağlantı kurulamadı"
        } catch (e: Exception) {
            showDialog = true
            exceptionText = "Bir şeyler yanlış gitti"
        }
    }
}