package com.sep.byteClub.ui.screen.secretHitler.board

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sep.byteClub.domain.entity.secretHitler.SecretHitlerCardEntity
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.screen.secretHitler.board.components.PresidentActionState
import com.sep.byteClub.ui.screen.secretHitler.board.components.action.ActionsLayout
import com.sep.byteClub.ui.screen.secretHitler.board.components.score.CardsSelection
import com.sep.byteClub.ui.screen.secretHitler.board.components.score.ScoreLayout

@Composable
fun BoardScreen(
    modifier: Modifier = Modifier,
    onNavigateToResult: (winner: String) -> Unit,
    viewModel: BoardViewModel = hiltViewModel()
) {
    val liberalScore = viewModel.liberalScore.collectAsStateWithLifecycle()
    val fascismScore = viewModel.fascismScore.collectAsStateWithLifecycle()
    val presidentActionState = viewModel.presidentActionState.collectAsStateWithLifecycle()


    LaunchedEffect(liberalScore.value, fascismScore.value) {
        if (liberalScore.value == 5) {
            onNavigateToResult.invoke(SecretHitlerCardEntity.LIBERAL.name)
        } else if (fascismScore.value == 6) {
            onNavigateToResult.invoke(SecretHitlerCardEntity.FASCISM.name)
        }
    }
    val players = viewModel.players.collectAsStateWithLifecycle()
    Column(modifier = modifier) {
        ScoreLayout(modifier = Modifier, liberalScore, fascismScore)
        when(presidentActionState.value) {
            is PresidentActionState.NoAction -> {
                CardsSelection(
                    modifier = Modifier,
                    getCardForPresident = viewModel::getCardForPresident,
                    submitCard = viewModel::submitCard,
                    removeCard = viewModel::removeCard
                )
            }
            else -> {
                ActionsLayout(
                    modifier = Modifier,
                    presidentActionState = presidentActionState,
                    players = players,
                    onPlayerClicked = viewModel::onPlayerRoleWatched
                )
            }
        }


    }
}


@Preview
@Composable
private fun BoardScreenPreview() {
    ByteClubTheme {
        BoardScreen(
            onNavigateToResult = {}
        )
    }
}