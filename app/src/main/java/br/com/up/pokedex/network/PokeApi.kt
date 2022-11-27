package br.com.up.pokedex.network

import br.com.up.pokedex.model.PokeDetailsResponse
import br.com.up.pokedex.model.PokeListResponse
import br.com.up.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class PokeApi {

    private var retrofit: Retrofit? = null
    private var pokeService: PokeApiService? = null


    init {

        retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        pokeService = retrofit?.create(PokeApiService::class.java)

    }


    fun getPokemons(listener: (List<Pokemon>?) -> Unit) {
        val call = pokeService?.getPokemons()
        call?.enqueue(object : Callback<PokeListResponse> {
            override fun onResponse(
                call: Call<PokeListResponse>,
                response: Response<PokeListResponse>
            ) {
                listener(response.body()?.pokemons)
            }

            override fun onFailure(
                call: Call<PokeListResponse>,
                t: Throwable
            ) {
                listener(null)
            }
        })
    }

    fun getPokemonDetail(id: String, listener: (PokeDetailsResponse?) -> Unit) {
        val call = pokeService?.getPokemonDetail(id)
        call?.enqueue(object : Callback<PokeDetailsResponse> {
            override fun onResponse(
                call: Call<PokeDetailsResponse>,
                response: Response<PokeDetailsResponse>
            ) {
                listener(response.body())
            }

            override fun onFailure(
                call: Call<PokeDetailsResponse>,
                t: Throwable
            ) {
                listener(null)
            }
        })
    }
}