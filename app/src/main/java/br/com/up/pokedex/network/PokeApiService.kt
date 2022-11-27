package br.com.up.pokedex.network

import br.com.up.pokedex.model.PokeDetailsResponse
import br.com.up.pokedex.model.PokeListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {

    @GET("pokemon?limit=151")
    fun getPokemons(): Call<PokeListResponse>

    @GET("pokemon/{id}")
    fun getPokemonDetail(
        @Path("id") id: String
    ): Call<PokeDetailsResponse>

}