package com.sep.byteClub.ui.screen.secretHitler.roles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.domain.usecase.secretHitler.SecretHitlerFetchPlayersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RolesViewModel(
    private val secretHitlerFetchPlayersUseCase: SecretHitlerFetchPlayersUseCase
) : ViewModel() {

    private val _players = MutableStateFlow<ArrayList<SecretHitlerPlayerEntity>>(arrayListOf())
    val players = _players.asStateFlow()

    init {
        fetchPlayers()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            secretHitlerFetchPlayersUseCase.invoke().collect { hitlerPlayerEntities ->
                _players.value = hitlerPlayerEntities as ArrayList<SecretHitlerPlayerEntity>
            }
        }
    }

}