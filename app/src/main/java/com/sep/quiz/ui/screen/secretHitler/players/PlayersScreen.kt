package com.sep.quiz.ui.screen.secretHitler.players

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.quiz.ui.designSystem.theme.QuizTheme
import com.sep.quiz.ui.screen.secretHitler.players.components.PlayersList

@Composable
internal fun PlayersScreen(
    modifier: Modifier = Modifier,
    viewModel: PlayersViewModel = hiltViewModel()
) {
    Column(modifier = modifier) {
        PlayersHeaderComponent()
//        PlayersList()
    }
}


@Composable
private fun PlayersHeaderComponent(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {

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