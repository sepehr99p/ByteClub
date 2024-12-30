package com.sep.byteClub.ui.screen.secretHitler.board.components.action

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.sep.byteClub.R
import com.sep.byteClub.domain.entity.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.ui.designSystem.theme.Bold_14
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_16
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8
import com.sep.byteClub.ui.screen.secretHitler.board.components.PresidentActionState

@Composable
internal fun ActionsLayout(
    modifier: Modifier = Modifier,
    presidentActionState: State<PresidentActionState>,
    players: State<ArrayList<SecretHitlerPlayerEntity>>,
    onPlayerClicked: (player: SecretHitlerPlayerEntity) -> Unit
) {
    Column(modifier = modifier) {
        val showPlayersBottomSheet = remember { mutableStateOf(false) }
        when (presidentActionState.value) {
            is PresidentActionState.NoAction -> {}
            else -> {
                PresidentRoleWatchingBtn(onClick = { showPlayersBottomSheet.value = true })
            }
        }
        if (showPlayersBottomSheet.value) {
            PresidentRolesWatching(
                players = players.value,
                showBottomSheet = showPlayersBottomSheet,
                onPlayerClicked = onPlayerClicked
            )
        }
    }
}

@Composable
private fun PresidentRoleWatchingBtn(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = modifier
                .align(Alignment.Center)
                .padding(padding_8)
                .clip(RoundedCornerShape(corner_16))
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(corner_16)
                )
                .padding(padding_8)
                .clickable { onClick.invoke() },
            text = stringResource(R.string.click_to_watch_role),
            color = MaterialTheme.colorScheme.onPrimary,
            style = Bold_14
        )

    }

}