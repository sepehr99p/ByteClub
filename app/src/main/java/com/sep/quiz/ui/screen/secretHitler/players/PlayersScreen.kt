package com.sep.quiz.ui.screen.secretHitler.players

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.quiz.R
import com.sep.quiz.ui.designSystem.components.button.ButtonComponent
import com.sep.quiz.ui.designSystem.theme.QuizTheme
import com.sep.quiz.ui.designSystem.theme.dimen.padding_8
import com.sep.quiz.ui.screen.secretHitler.players.components.PlayersList

@Composable
fun PlayersScreen(
    modifier: Modifier = Modifier,
    viewModel: PlayersViewModel = hiltViewModel()
) {
    val players = viewModel.players.collectAsState()
    Column(modifier = modifier) {
        PlayersHeaderComponent()
        PlayersList(players = players.value)
        StartGameBtn(onClick = {})
    }
}


@Composable
private fun PlayersHeaderComponent(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {

    }
}

@Composable
private fun StartGameBtn(modifier: Modifier = Modifier, onClick: () -> Unit) {
    ButtonComponent(
        modifier = modifier.fillMaxWidth().padding(vertical = padding_8),
        onclick = onClick,
        title = stringResource(id = R.string.start)
    )
}

@Preview
@Composable
private fun StartGameBtnPreview() {
    QuizTheme {
        StartGameBtn(onClick = {})
    }
}

@Preview
@Composable
private fun PlayersHeaderComponentPreview() {
    QuizTheme {
        PlayersHeaderComponent()
    }
}

@Preview
@Composable
private fun PlayersScreenPreview() {
    QuizTheme {
        PlayersScreen()
    }
}