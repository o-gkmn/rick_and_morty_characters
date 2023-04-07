package com.inviostajyer.rickandmortycharacters.data.interfaces

import com.inviostajyer.rickandmortycharacters.domain.model.Location
import com.inviostajyer.rickandmortycharacters.domain.model.LocationList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationApi {
    @GET("/api/location?page=1")
    fun getAllLocations() : Call<LocationList>

    @GET("/api/location/{id}")
    fun getLocation(@Path("id") id : Int) : Call<Location>
}