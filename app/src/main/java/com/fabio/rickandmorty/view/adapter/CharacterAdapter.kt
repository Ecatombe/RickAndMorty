package com.fabio.rickandmorty.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fabio.rickandmorty.CharactersListQuery
import com.fabio.rickandmorty.R
import com.fabio.rickandmorty.databinding.ItemCharacterBinding

class CharacterAdapter :
    ListAdapter<CharactersListQuery.Result, CharacterViewHolder>(CharacterDiffUtil()) {

    var onItemClicked: ((CharactersListQuery.Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemCharacterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_character,
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.binding.character = getItem(position)

        val character = getItem(position)
        holder.binding.root.setOnClickListener {
            onItemClicked?.invoke(character)
        }

    }

}

class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

class CharacterDiffUtil : DiffUtil.ItemCallback<CharactersListQuery.Result>() {

    override fun areItemsTheSame(
        oldItem: CharactersListQuery.Result,
        newItem: CharactersListQuery.Result
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CharactersListQuery.Result,
        newItem: CharactersListQuery.Result
    ): Boolean {
        return oldItem == newItem
    }

}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    imageView.load(url) { crossfade(true) }
}