package com.sep.byteClub.ui.screen.secretHitler.players

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.domain.usecase.secretHitler.SecretHitlerFetchPlayersUseCase
import com.sep.byteClub.domain.usecase.secretHitler.SecretHitlerSetPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val fetchPlayersUseCase: SecretHitlerFetchPlayersUseCase,
    private val setPlayersUseCase: SecretHitlerSetPlayersUseCase
) : ViewModel() {

    private val _players = MutableStateFlow<ArrayList<String>>(arrayListOf())
    val players = _players.asStateFlow()
    val playersSnapshot = mutableStateListOf<String>()

    fun addPlayer(name: String) {
        val temp = arrayListOf(name)
        temp.addAll(_players.value)
        _players.value = temp
        playersSnapshot.add(name)
    }

    fun removePlayer(name: String) {
        val temp = _players.value
        temp.remove(name)
        _players.value = temp
        playersSnapshot.remove(name)
    }

    init {
        fetchPlayers()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            fetchPlayersUseCase.invoke().collect { hitlerPlayerEntities ->
                _players.value = hitlerPlayerEntities.map { it.name } as ArrayList<String>
                playersSnapshot.addAll(_players.value)
            }
        }
    }

    fun setPlayers() {
        viewModelScope.launch {
            setPlayersUseCase.invoke(players.value)
        }
    }

}