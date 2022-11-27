package br.com.up.pokedex.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.PokeDetailsActivity
import br.com.up.pokedex.R
import br.com.up.pokedex.databinding.PokeItemViewBinding
import br.com.up.pokedex.model.Pokemon
import br.com.up.pokedex.utils.capitalizeWords
import com.squareup.picasso.Picasso


class PokeAdapter(private val pokemons: List<Pokemon>) :
    RecyclerView.Adapter<PokeAdapter.PokeViewHolder>() {

    inner class PokeViewHolder(itemView: PokeItemViewBinding) :
        RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PokeItemViewBinding.inflate(layoutInflater, parent, false)
        return PokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {

        val pokemon = pokemons[position]

        //https://pokeapi.co/api/v2/pokemon/1/"
        val id = pokemon.url.replace(
            "https://pokeapi.co/api/v2/pokemon/",
            ""
        ).replace("/", "")

        val url =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

        val imageView: ImageView = holder.itemView.findViewById(R.id.poke_image)
        val textView: TextView = holder.itemView.findViewById(R.id.poke_name)
        val cardView: CardView = holder.itemView.findViewById(R.id.poke_card)

        cardView.setOnClickListener{
            val intent = Intent(holder.itemView.context, PokeDetailsActivity::class.java).apply { putExtra("id", id) }
            holder.itemView.context.startActivity(intent)
        }

        textView.text = pokemon.name.capitalizeWords()
        Picasso.get().load(url).into(imageView)
    }


    override fun getItemCount(): Int {
        return pokemons.size
    }
}