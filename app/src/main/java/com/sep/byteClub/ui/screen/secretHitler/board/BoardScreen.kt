package com.sep.byteClub.ui.screen.secretHitler.board

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.screen.secretHitler.board.components.action.ActionsLayout
import com.sep.byteClub.ui.screen.secretHitler.board.components.score.CardsSelection
import com.sep.byteClub.ui.screen.secretHitler.board.components.score.ScoreLayout

@Composable
fun BoardScreen(
    modifier: Modifier = Modifier,
    viewModel: BoardViewModel = hiltViewModel()
) {
    val liberalScore = viewModel.liberalScore.collectAsStateWithLifecycle()
    val fascismScore = viewModel.fascismScore.collectAsStateWithLifecycle()
    val players = viewModel.players.collectAsStateWithLifecycle()
    Column(modifier = modifier) {
        ScoreLayout(modifier = Modifier, liberalScore, fascismScore)
        ActionsLayout(modifier = Modifier, fascismScore = fascismScore, players = players)
        CardsSelection(
            modifier = Modifier,
            getCardForPresident = viewModel::getCardForPresident,
            submitCard = viewModel::submitCard,
            removeCard = viewModel::removeCard
        )
    }
}


@Preview
@Composable
private fun BoardScreenPreview() {
    ByteClubTheme {
        BoardScreen()
    }
}