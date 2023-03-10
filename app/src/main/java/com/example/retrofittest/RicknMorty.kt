package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.retrofittest.databinding.FragmentRicknMortyBinding
import com.example.retrofittest.network.ApiClient
import com.example.retrofittest.network.CharacterResponse
import retrofit2.Call
import retrofit2.Response

class RicknMorty : Fragment () {

    private lateinit var binding: FragmentRicknMortyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRicknMortyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    private fun setUpUI() {

        binding.charactersRv.apply {
            setHasFixedSize(true)

//            use from page 1
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
                            recyclerView.layoutManager =
                                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                            recyclerView.adapter = adapter
                        }
                    }
                }
                //if it was not successful
                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                    Log.e("Failed", "" + t.message)
                }
            })
        }
    }
}
