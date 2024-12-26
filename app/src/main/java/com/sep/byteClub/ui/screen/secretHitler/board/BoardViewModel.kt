package com.sep.byteClub.ui.screen.secretHitler.board

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerCardEntity
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.domain.usecase.secretHitler.SecretHitlerFetchPlayersUseCase
import com.sep.byteClub.ui.screen.secretHitler.board.components.PresidentActionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
    val unUsedCards = mutableStateListOf<SecretHitlerCardEntity>()
    val fascismScore = MutableStateFlow(0)
    val liberalScore = MutableStateFlow(0)
    val _presidentActionState =
        MutableStateFlow<PresidentActionState>(PresidentActionState.NoAction)
    val presidentActionState = _presidentActionState.asStateFlow()
    val firstSeenPlayer = MutableStateFlow<String?>(null)
    val secondSeenPlayer = MutableStateFlow<String?>(null)

    init {
        fetchPlayers()
        initCardsToPlay()
    }

    private fun updatePresidentActionState() {
        viewModelScope.launch {
            delay(5000)
            if (fascismScore.value == 2 && firstSeenPlayer.value == null) {
                _presidentActionState.emit(PresidentActionState.FirstWatchRole)
            } else if (fascismScore.value == 3 && secondSeenPlayer.value == null) {
                _presidentActionState.emit(PresidentActionState.SecondWatchRole(firstSeenPlayer.value.orEmpty()))
            } else {
                _presidentActionState.emit(PresidentActionState.NoAction)
            }
        }
    }


    fun onPlayerRoleWatched(secretHitlerPlayerEntity: SecretHitlerPlayerEntity) {
        if (fascismScore.value == 2) {
            firstSeenPlayer.value = secretHitlerPlayerEntity.name
        } else {
            secondSeenPlayer.value = secretHitlerPlayerEntity.name
        }
        updatePresidentActionState()
    }

    fun submitCard(card: SecretHitlerCardEntity) {
        if (card == SecretHitlerCardEntity.LIBERAL) {
            liberalScore.value += 1
        } else {
            fascismScore.value += 1
        }
        usedCards.value.add(card)
        updatePresidentActionState()
    }

    fun removeCard(card: SecretHitlerCardEntity) = usedCards.value.add(card)

    fun getCardForPresident(): ArrayList<SecretHitlerCardEntity> {
        if (unUsedCards.isEmpty() || unUsedCards.size < 3) {
            shuffleUsedCards()
        }
        val newList = arrayListOf<SecretHitlerCardEntity>()
        repeat(3) { newList.add(unUsedCards.removeAt(0)) }
        return newList
    }

    private fun shuffleUsedCards() {
        unUsedCards.addAll(usedCards.value.shuffled())
    }

    private fun initCardsToPlay() {
        repeat(13) { unUsedCards.add(SecretHitlerCardEntity.FASCISM) }
        repeat(7) { unUsedCards.add(SecretHitlerCardEntity.LIBERAL) }
        unUsedCards.shuffle()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            secretHitlerFetchPlayersUseCase.invoke().collect {
                _players.value = it as ArrayList<SecretHitlerPlayerEntity>
            }
        }
    }

}