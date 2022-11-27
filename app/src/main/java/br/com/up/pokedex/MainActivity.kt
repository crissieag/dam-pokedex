package br.com.up.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.adapter.PokeAdapter
import br.com.up.pokedex.model.Pokemon
import br.com.up.pokedex.network.PokeApi
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    var pokeList = mutableListOf<Pokemon>()
    var filteredPokeList = mutableListOf<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.poke_recycler)
        val searchEditText: TextInputEditText = findViewById(R.id.searchEditText)
        val errorMessage: TextView = findViewById(R.id.errorMessage)

        recyclerView.layoutManager =
            GridLayoutManager(this, 3)

        PokeApi().getPokemons { pokemons ->
            this.pokeList = pokemons as MutableList<Pokemon>
            filteredPokeList = pokemons
            recyclerView.adapter = PokeAdapter(pokemons)
        }


        //cria listener para alteração do valor do input
        searchEditText.doOnTextChanged { inputText, _, _, _ ->
            if (inputText.isNullOrEmpty())
                filteredPokeList = pokeList

            //compara através de substrings os caracteres do input com os itens pertencentes a lista de pokemons da primeira requisição
            filteredPokeList = pokeList.filter { it.name.contains(inputText!!) }.toMutableList()

            //cria nova lista, se a lista não estiver vazia, se estiver vazia retorna 'pokemon não encontrado
            errorMessage.isVisible = filteredPokeList.isEmpty()

            recyclerView.adapter = PokeAdapter(filteredPokeList)
        }
    }
}


