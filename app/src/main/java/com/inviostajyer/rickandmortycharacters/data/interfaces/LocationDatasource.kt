package com.inviostajyer.rickandmortycharacters.data.interfaces

import com.inviostajyer.rickandmortycharacters.domain.model.Location

interface LocationDatasource {
    suspend fun getAllLocations() : List<Location>
    suspend fun getLocation(id : Int) : Location
}