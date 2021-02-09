package com.example.desafio_android_jose_rubens.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafio_android_jose_rubens.ComicActivity
import com.example.desafio_android_jose_rubens.R
import com.example.desafio_android_jose_rubens.models.response.CharacterResponse
import kotlinx.android.synthetic.main.layou_character_list_item.view.*

class CharacterRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<CharacterResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterListViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.layou_character_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CharacterListViewHolder ->{
                holder.bind(items[position])
            }
        }

        holder.itemView.btn_comic.setOnClickListener {
            val intent = Intent(holder.itemView.context, ComicActivity::class.java)
            intent.putExtra("characterId", holder.itemView.character_id.text)
            intent.putExtra("characterName", holder.itemView.character_name.text)
            intent.putExtra("characterDescription", holder.itemView.character_description.text)

            holder.itemView.context.startActivity(intent)
            (holder.itemView.context as Activity).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);


        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CharacterListViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView){

        val characterImage : ImageView = itemView.character_image
        val characterName : TextView = itemView.character_name
        val characterDescription : TextView = itemView.character_description
        val characterId : TextView = itemView.character_id
        val btnComic : Button = itemView.btn_comic;

        fun bind(character: CharacterResponse){

            characterName.text = character.name
            characterDescription.text = character.description
            characterId.text = character.id.toString()


            Glide.with(itemView.context)
                .load("${character.thumbnail.path}.${character.thumbnail.extension}")
                .into(characterImage)
        }
    }

    fun  submitList(characterList: List<CharacterResponse>){
            items = characterList
    }
}