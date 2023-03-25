package com.game.byteclub.viewModel

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.game.byteclub.R
import com.game.byteclub.model.Game
import com.game.byteclub.view.adapter.GamesAdapter

class HomeViewModel : ViewModel() {

    var dowrTxt = "Dowr"
    private val gamesAdapter: GamesAdapter by lazy {
        GamesAdapter(mutableListOf(
            Game("Dowr",R.id.dowrHomeFragment),
            Game("Spy Fall",R.id.dowrHomeFragment) //todo : change it later
        ))
    }

   fun initRecyclerView(recyclerView: RecyclerView) = recyclerView.apply {
        adapter = gamesAdapter
        val lm = LinearLayoutManager(recyclerView.context)
        lm.apply {
            orientation = LinearLayoutManager.VERTICAL
            reverseLayout = true
        }
        layoutManager = lm
    }

}
