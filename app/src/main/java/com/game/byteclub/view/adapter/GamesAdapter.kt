package com.game.byteclub.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.game.byteclub.databinding.GamesItemBinding
import com.game.byteclub.model.Game

class GamesAdapter(private val gamesList: MutableList<Game>) :
    RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(GamesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply {
            bind(gamesList[position])
            itemView.setOnClickListener {
                itemView.findNavController().navigate(gamesList[position].nav)
            }
        }
    }

    override fun getItemCount() = gamesList.size


    class ViewHolder(private var binding: GamesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Game) {
            binding.apply {
                game = item
                executePendingBindings()
            }
        }
    }

}