package com.sep.quiz.ui.screen.secretHitler.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.quiz.domain.usecase.secretHitler.SecretHitlerFetchPlayersUseCase
import com.sep.quiz.domain.usecase.secretHitler.SecretHitlerSetPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val fetchPlayersUseCase: SecretHitlerFetchPlayersUseCase,
    private val setPlayersUseCase: SecretHitlerSetPlayersUseCase
) : ViewModel() {



    init {
        fetchPlayers()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            fetchPlayersUseCase.invoke()
        }
    }

    fun setPlayers() {

    }

}