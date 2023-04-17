package com.inviostajyer.rickandmortycharacters.domain.model

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Map<String, String>,
    val location: Map<String, String>,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
) {
    companion object {
        fun emptyCharacter(): Character = Character(
            id = -1,
            name = "",
            status = "",
            species = "",
            type = "",
            gender = "",
            origin = mapOf<String, String>("name" to "", "url" to ""),
            location = mapOf<String, String>("name" to "", "url" to ""),
            image = "",
            episode = arrayListOf(),
            url = "",
            created = ""
        )
    }
}
