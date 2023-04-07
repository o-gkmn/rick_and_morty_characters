package com.inviostajyer.rickandmortycharacters.domain.model

import com.google.gson.annotations.SerializedName


data class LocationList(
    @SerializedName("results")
    val locationList : ArrayList<Location>
)
