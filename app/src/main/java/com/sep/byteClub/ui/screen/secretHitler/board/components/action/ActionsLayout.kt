package com.sep.byteClub.ui.screen.secretHitler.board.components.action

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.ui.screen.secretHitler.board.components.PresidentRolesWatching

@Composable
fun ActionsLayout(
    modifier: Modifier = Modifier,
    fascismScore: State<Int>,
    players: State<ArrayList<SecretHitlerPlayerEntity>>
) {
    Column(modifier = modifier) {
        val showPresidentWatchRoleAction = remember { mutableStateOf(false) }
        LaunchedEffect(key1 = fascismScore.value) {
            if (fascismScore.value == 2) {
                //todo : this will be changed later
                showPresidentWatchRoleAction.value = true
            }
        }
        if (showPresidentWatchRoleAction.value) {
            PresidentRolesWatching(
                players = players.value,
                showBottomSheet = showPresidentWatchRoleAction
            )
        }
    }
}