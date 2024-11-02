package com.sep.byteClub.ui.screen.secretHitler.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sep.byteClub.ui.navigation.winnerArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SecretHitlerResultViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val winner = MutableStateFlow(savedStateHandle.get<String>(winnerArg))

}