package com.sep.byteClub.ui.screen.secretHitler.board.components

sealed class PresidentActionState {
    data object FirstWatchRole : PresidentActionState()
    data class SecondWatchRole(val lastSeenPlayer: String) : PresidentActionState()
    data object NoAction : PresidentActionState()
}