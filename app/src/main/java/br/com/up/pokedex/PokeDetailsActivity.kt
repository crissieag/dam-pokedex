package br.com.up.pokedex

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.databinding.ActivityPokeDetailsBinding
import br.com.up.pokedex.network.PokeApi
import br.com.up.pokedex.utils.capitalizeWords
import com.squareup.picasso.Picasso

class PokeDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityPokeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokeDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val id = intent.getStringExtra("id")

        val imageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

        Picasso.get().load(imageUrl).into(binding.imageView)

        PokeApi().getPokemonDetail(id!!) { pokemon ->
            binding.pokeName.text = pokemon?.name?.capitalizeWords()
            binding.typedesc.text =
                pokemon?.types?.joinToString { pokemonType -> pokemonType.type.name }
            binding.habilitiesDesc.text =
                pokemon?.abilities?.joinToString { pokemonAbility -> pokemonAbility.ability.name }
            binding.movesDesc.text =
                pokemon?.moves?.joinToString { pokemonMove -> pokemonMove.move.name }
            binding.statsDesc.text =
                pokemon?.stat?.joinToString (separator = "\n"){ pokemonStat -> "${pokemonStat.stat.name}: ${pokemonStat.baseStat}" }
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }
}