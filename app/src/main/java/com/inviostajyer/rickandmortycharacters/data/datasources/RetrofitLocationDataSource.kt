package com.inviostajyer.rickandmortycharacters.data.datasources

import com.inviostajyer.rickandmortycharacters.data.interfaces.LocationApi
import com.inviostajyer.rickandmortycharacters.data.interfaces.LocationDatasource
import com.inviostajyer.rickandmortycharacters.domain.model.Location
import javax.inject.Inject

class RetrofitLocationDataSource @Inject constructor(private val locationApi: LocationApi) : LocationDatasource {
    override suspend fun getAllLocations(): List<Location> {
        val locationList = locationApi.getAllLocations().execute()

        if (locationList.isSuccessful) {
            return locationList.body()!!.locationList
        } else {
            throw Exception("Bir sorun oluştu")
        }
    }

    override suspend fun getLocation(id: Int): Location {
        val location = locationApi.getLocation(id).execute()
        if (location.isSuccessful) {
            return location.body()!!
        } else {
            throw Exception("Bir sorun oluştu")
        }
    }
}