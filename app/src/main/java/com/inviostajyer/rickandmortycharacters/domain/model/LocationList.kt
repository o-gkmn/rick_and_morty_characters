package com.inviostajyer.rickandmortycharacters.domain.model

import com.google.gson.annotations.SerializedName


data class LocationList(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val data: ArrayList<Location>
) {
    companion object {
        fun emptyLocationList(): LocationList {
            return LocationList(data = arrayListOf(), info = Info.emptyInfo())
        }
    }
}


data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String,
) {
    companion object {
        fun emptyInfo(): Info {
            return Info(
                count = 0,
                pages = 0,
                next = "",
                prev = "",
            )
        }
    }
}
