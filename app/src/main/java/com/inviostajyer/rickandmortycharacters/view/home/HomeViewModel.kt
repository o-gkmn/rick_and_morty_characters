package com.inviostajyer.rickandmortycharacters.view.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.inviostajyer.rickandmortycharacters.domain.interfaces.LocationRepository
import com.inviostajyer.rickandmortycharacters.domain.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel() {
    val emptyLocation = Location(-1,"","","", ArrayList<String>(), "", "")
    var selectedChip by mutableStateOf(emptyLocation)
    lateinit var locationList : List<Location>

    fun getAllLocation (){
        locationList =  runBlocking { withContext(Dispatchers.IO) {locationRepository.getAllLocations() } }
    }
}