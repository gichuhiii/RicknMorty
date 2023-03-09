package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.retrofittest.network.ApiClient
import com.example.retrofittest.network.CharacterResponse
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        //use from page 1
        val client = ApiClient.apiService.fetchCharacters("1")

        client.enqueue(object : retrofit2.Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                //check if response is successful and display response body
                if (response.isSuccessful) {
                    Log.d("characters", "" + response.body())

                    //get results from response body
                    val results = response.body()?.results
                    results?.forEach {
                       val adapter = MainAdapter(results)
                        val recyclerView = findViewById<RecyclerView>(R.id.charactersRv)
                        recyclerView.layoutManager = StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL)
                        recyclerView.adapter = adapter
                    }
                }
            }

            //if it was not successful
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("Failed","" +t.message)
            }
        })
    }
}