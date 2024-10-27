package com.sep.byteClub.ui.screen.secretHitler.board

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerCardEntity
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.domain.usecase.secretHitler.SecretHitlerFetchPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val secretHitlerFetchPlayersUseCase: SecretHitlerFetchPlayersUseCase
) : ViewModel() {

    private val _players = MutableStateFlow<ArrayList<SecretHitlerPlayerEntity>>(arrayListOf())
    val players = _players.asStateFlow()

    private val usedCards = MutableStateFlow<ArrayList<SecretHitlerCardEntity>>(arrayListOf())
    val unUsedCards = MutableStateFlow<ArrayList<SecretHitlerCardEntity>>(arrayListOf())
    val fascismScore = MutableStateFlow(0)
    val liberalScore = MutableStateFlow(0)

    init {
        fetchPlayers()
        initCardsToPlay()
    }

    fun submitCard(card: SecretHitlerCardEntity) {
        if (card == SecretHitlerCardEntity.LIBERAL) {
            liberalScore.value += 1
        } else {
            fascismScore.value += 1
        }
        usedCards.value.add(card)
    }

    fun removeCard(card: SecretHitlerCardEntity) {
        usedCards.value.add(card)
    }

    fun getCardForPresident(): ArrayList<SecretHitlerCardEntity> {
        if (unUsedCards.value.isEmpty() || unUsedCards.value.size < 3) {
            shuffleUsedCards()
        }
        val newList = arrayListOf<SecretHitlerCardEntity>()
        for (i in 0 until 3) {
            newList.add(unUsedCards.value.removeFirst())
        }
        Log.i("TAG", "getCardForPresident: ")
        return newList
    }

    private fun shuffleUsedCards() {
        unUsedCards.value.addAll(usedCards.value.shuffled())
    }

    private fun initCardsToPlay() {
        for (i in 0 until 13) {
            unUsedCards.value.add(SecretHitlerCardEntity.FASCISM)
        }
        for (i in 0 until 7) {
            unUsedCards.value.add(SecretHitlerCardEntity.LIBERAL)
        }
        unUsedCards.value.shuffle()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            secretHitlerFetchPlayersUseCase.invoke().collect {
                _players.value = it as ArrayList<SecretHitlerPlayerEntity>
            }
        }
    }

}