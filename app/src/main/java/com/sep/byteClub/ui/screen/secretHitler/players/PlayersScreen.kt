package com.sep.byteClub.ui.screen.secretHitler.players

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.screen.secretHitler.players.components.AddPlayerBottomSheet
import com.sep.byteClub.ui.screen.secretHitler.players.components.PlayersHeaderComponent
import com.sep.byteClub.ui.screen.secretHitler.players.components.PlayersList
import com.sep.byteClub.ui.screen.secretHitler.players.components.StartGameBtn

@Composable
fun PlayersScreen(
    modifier: Modifier = Modifier,
    viewModel: PlayersViewModel = hiltViewModel(),
    onNavigateToRole: () -> Unit
) {
    val players = viewModel.players
    val showAddPlayerBS = remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        PlayersHeaderComponent()
        PlayersList(
            modifier = Modifier.weight(1f),
            players = players,
            onAddPlayerClicked = {
                showAddPlayerBS.value = true
            },
            onPlayerRemoved = {
                viewModel.removePlayer(it)
            }
        )
        StartGameBtn(players = players, onClick = {
            viewModel.setPlayers()
            onNavigateToRole.invoke()
        })
    }
    if (showAddPlayerBS.value) {
        AddPlayerBottomSheet(
            showBottomSheet = showAddPlayerBS,
            onPlayerAdded = { name ->
                viewModel.addPlayer(name)
            }
        )
    }
}


@Preview
@Composable
private fun PlayersScreenPreview() {
    ByteClubTheme {
        PlayersScreen(onNavigateToRole = {})
    }
}