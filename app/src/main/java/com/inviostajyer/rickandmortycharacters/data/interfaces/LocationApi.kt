package com.inviostajyer.rickandmortycharacters.data.interfaces

import com.inviostajyer.rickandmortycharacters.domain.model.Character
import com.inviostajyer.rickandmortycharacters.domain.model.Location
import com.inviostajyer.rickandmortycharacters.domain.model.LocationList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("/api/location")
    fun getAllLocations(@Query("page") pageNum : Int) : Call<LocationList>

    @GET("/api/location/{id}")
    fun getLocation(@Path("id") id : Int) : Call<Location>

    @GET("/api/character/{characterIds}")
    fun getAllCharactersByLocation(@Path("characterIds") characterIds : List<Int>) : Call<List<Character>>

    @GET("/api/character/{id}")
    fun getCharacter(@Path("id") id : Int) : Call<Character>
}