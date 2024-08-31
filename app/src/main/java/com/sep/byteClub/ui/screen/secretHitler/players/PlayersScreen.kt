package com.sep.byteClub.ui.screen.secretHitler.players

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.byteClub.R
import com.sep.byteClub.ui.designSystem.components.button.ButtonComponent
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8
import com.sep.byteClub.ui.screen.secretHitler.players.components.AddPlayerBottomSheet
import com.sep.byteClub.ui.screen.secretHitler.players.components.PlayersList

@Composable
fun PlayersScreen(
    modifier: Modifier = Modifier,
    viewModel: PlayersViewModel = hiltViewModel(),
    onNavigateToBoard: () -> Unit
) {
    val players = viewModel.players.collectAsState()
    val showAddPlayerBS = remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        PlayersHeaderComponent()
        PlayersList(players = players, onAddPlayerClicked = {
            showAddPlayerBS.value = true
        })
        StartGameBtn(players = players, onClick = {
            viewModel.setPlayers()
            onNavigateToBoard.invoke()
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


@Composable
private fun PlayersHeaderComponent(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {

    }
}

@Composable
private fun StartGameBtn(
    modifier: Modifier = Modifier,
    players: State<List<String>>,
    onClick: () -> Unit
) {
    ButtonComponent(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8),
        onclick = onClick,
        isDisabled = players.value.size > 4,
        title = stringResource(id = R.string.start)
    )
}

@Preview
@Composable
private fun StartGameBtnPreview() {
    ByteClubTheme {
        val test = remember { mutableStateOf(listOf("")) }
        StartGameBtn(onClick = {}, players = test)
    }
}

@Preview
@Composable
private fun PlayersHeaderComponentPreview() {
    ByteClubTheme {
        PlayersHeaderComponent()
    }
}

@Preview
@Composable
private fun PlayersScreenPreview() {
    ByteClubTheme {
        PlayersScreen(onNavigateToBoard = {})
    }
}