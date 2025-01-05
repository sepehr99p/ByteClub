package com.sep.byteClub.ui.screen.secretHitler.players

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.domain.usecase.secretHitler.FetchSecretHitlerPlayersUseCase
import com.sep.byteClub.domain.usecase.secretHitler.UpdateSecretHitlerPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val fetchPlayersUseCase: FetchSecretHitlerPlayersUseCase,
    private val setPlayersUseCase: UpdateSecretHitlerPlayersUseCase
) : ViewModel() {

    val players = mutableStateListOf<String>()

    fun addPlayer(name: String) = viewModelScope.launch {
        players.add(name)
    }

    fun removePlayer(name: String) = players.remove(name)

    init {
        fetchPlayers()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            fetchPlayersUseCase.invoke().collect { hitlerPlayerEntities ->
                players.clear()
                players.addAll(hitlerPlayerEntities.map { it.name } as ArrayList<String>)
            }
        }
    }

    fun submitPlayers() = viewModelScope.launch {
        setPlayersUseCase.invoke(players.toList())
    }


}