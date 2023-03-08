package com.example.retrofittest.network

import com.squareup.moshi.Json

data class Character(

    @Json(name = "name")
    val name: String,
    @Json(name = "image")
    val image: String,

)

//gives us results
data class CharacterResponse(

    @Json(name = "results")
    val results: List<Character>

)