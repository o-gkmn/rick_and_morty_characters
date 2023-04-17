package com.inviostajyer.rickandmortycharacters.domain.model

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
) {
    companion object {
        fun emptyLocation(): Location =
            Location(
                id = -1,
                name = "",
                type = "",
                dimension = "",
                residents = arrayListOf(),
                url = "",
                created = ""
            )
    }
}
