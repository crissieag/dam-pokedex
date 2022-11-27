package br.com.up.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokeListResponse(
    @SerializedName("results")
    val pokemons: List<Pokemon>
)

data class PokeDetailsResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("abilities")
    val abilities: List<PokemonAbility>,

    @SerializedName("moves")
    val moves: List<PokemonMove>,

    @SerializedName("stats")
    val stat: List<PokemonStat>,

    @SerializedName("types")
    val types: List<PokemonType>?,
)

data class PokemonMove(
    @SerializedName("move")
    val move: NameAndUrl
)

data class PokemonAbility(
    @SerializedName("ability")
    val ability: NameAndUrl,

    @SerializedName("is_hidden")
    val isHidden: Boolean,

    @SerializedName("slot")
    val slot: Int
)

data class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int?,

    @SerializedName("effort")
    val effort: Int?,

    @SerializedName("stat")
    val stat: NameAndUrl
)

data class PokemonType(
    @SerializedName("slot")
    val slot: Int,

    @SerializedName("type")
    val type: NameAndUrl
)

data class NameAndUrl(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
)

