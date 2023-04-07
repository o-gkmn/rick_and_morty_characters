package com.inviostajyer.rickandmortycharacters.domain.interfaces

import com.inviostajyer.rickandmortycharacters.domain.model.Location

interface LocationRepository {
    suspend fun getAllLocations() : List<Location>
    suspend fun getLocation(id : Int) : Location
}