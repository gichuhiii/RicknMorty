package com.example.retrofittest.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//to call API service
object ApiClient {

    //get URL
    private val BASE_URL = "https://rickandmortyapi.com/api/"

    //create variable for Moshi builder and add converter
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // create instance of Retrofit by lazy so it can be initialized only when needed
    private val retrofit: Retrofit by lazy {
        //build with Retrofit
        Retrofit.Builder()

            //pass base URL and Moshi variables
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    //allow us to use API service to get characters
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

//interface to define how retrofit talks to the service using GET method
interface ApiService {

    //get characters and fetch characters with query and call to get the character response
    @GET("character")

    fun fetchCharacters(@Query("page") page: String): Call<CharacterResponse>

}