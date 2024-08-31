package com.sep.byteClub.ui.screen.secretHitler.players

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

    fun addPlayer(name : String) {
        _players.value.add(name)
    }

    init {
        fetchPlayers()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            fetchPlayersUseCase.invoke().collect { hitlerPlayerEntities ->
                _players.value = hitlerPlayerEntities.map { it.name } as ArrayList<String>
            }
        }
    }

    fun setPlayers() {

    }

}