package com.inviostajyer.rickandmortycharacters.view.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.inviostajyer.rickandmortycharacters.domain.interfaces.RickAndMortyRepository
import com.inviostajyer.rickandmortycharacters.domain.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DetailsViewModel @Inject constructor(rickAndMortyRepository: RickAndMortyRepository, val id: Int) : ViewModel() {

    var selectedCharacter: Character

    var showDialog by mutableStateOf(false)
    lateinit var exceptionText: String

    init {
        try {
            selectedCharacter = runBlocking { withContext(Dispatchers.IO) { rickAndMortyRepository.getCharacters(id) } }
        } catch (e: IOException) {
            showDialog = true
            selectedCharacter = Character.emptyCharacter()
            exceptionText = "İnternet bağlantısı kurulamadı"
        } catch (e: HttpException) {
            showDialog = true
            selectedCharacter = Character.emptyCharacter()
            exceptionText = "Uzak sunucuyla bağlantı kurulamadı"
        } catch (e: Exception) {
            showDialog = true
            selectedCharacter = Character.emptyCharacter()
            exceptionText = "Bir şeyler yanlış gitti"
        }
    }
}