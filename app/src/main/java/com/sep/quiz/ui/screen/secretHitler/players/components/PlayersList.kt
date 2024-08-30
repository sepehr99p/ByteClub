package com.sep.quiz.ui.screen.secretHitler.players.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sep.quiz.ui.designSystem.theme.QuizTheme

@Composable
internal fun PlayersList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {

    }
}

@Composable
internal fun PlayersItemComponent(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {

    }
}

@Composable
internal fun AddNewPlayerComponent(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {

    }
}

@Preview
@Composable
private fun AddNewPlayerComponentPreview() {
    QuizTheme {
        AddNewPlayerComponent()
    }
}

@Preview
@Composable
private fun PlayersItemComponentPreview() {
    QuizTheme {
        PlayersItemComponent()
    }
}

@Preview
@Composable
private fun PlayersListPreview() {
    QuizTheme {
        PlayersList()
    }
}