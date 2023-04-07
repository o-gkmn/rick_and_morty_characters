package com.inviostajyer.rickandmortycharacters.domain.repository

import com.inviostajyer.rickandmortycharacters.data.interfaces.LocationDatasource
import com.inviostajyer.rickandmortycharacters.domain.interfaces.LocationRepository
import com.inviostajyer.rickandmortycharacters.domain.model.Location

class LocationRepositoryImpl(private val locationDatasource: LocationDatasource) : LocationRepository {
    override suspend fun getAllLocations(): List<Location> {
        return locationDatasource.getAllLocations()
    }

    override suspend fun getLocation(id : Int): Location {
        return locationDatasource.getLocation(id)
    }
}