package com.sep.byteClub.ui.screen.secretHitler.board.components.score

internal sealed class SelectionState {
    data object Init : SelectionState()
    data object PresidentSelection : SelectionState()
    data object Pause : SelectionState()
    data object MinisterSelection : SelectionState()
}