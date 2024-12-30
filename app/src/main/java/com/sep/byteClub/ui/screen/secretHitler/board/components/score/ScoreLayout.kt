package com.sep.byteClub.ui.screen.secretHitler.board.components.score

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sep.byteClub.domain.entity.secretHitler.SecretHitlerCardEntity
import com.sep.byteClub.ui.designSystem.theme.Bold_18
import com.sep.byteClub.ui.designSystem.theme.Bold_20
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8

@Composable
internal fun ScoreLayout(
    modifier: Modifier = Modifier,
    liberalScore: State<Int>,
    fascismScore: State<Int>
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(padding_8),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ScoreItemComponent(cardEntity = SecretHitlerCardEntity.LIBERAL, score = liberalScore)
        ScoreItemComponent(cardEntity = SecretHitlerCardEntity.FASCISM, score = fascismScore)
    }
}

@Composable
private fun RowScope.ScoreItemComponent(
    modifier: Modifier = Modifier,
    cardEntity: SecretHitlerCardEntity,
    score: State<Int>
) {
    Column(
        modifier = modifier.weight(1f).padding(padding_8),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = cardEntity.name, color = cardEntity.color, style = Bold_20)
        Spacer(modifier = Modifier.height(padding_8))
        Text(
            text = score.value.toString(),
            color = cardEntity.color,
            style = Bold_18
        )
    }
}

@Preview
@Composable
private fun ScoreItemComponentPreview() {
    ByteClubTheme {
        val test = remember { mutableIntStateOf(2) }
        Row {
            ScoreItemComponent(score = test, cardEntity = SecretHitlerCardEntity.FASCISM)
        }
    }
}

@Preview
@Composable
private fun ScoreLayoutPreview() {
    ByteClubTheme {
        val test = remember { mutableIntStateOf(1) }
        ScoreLayout(liberalScore = test, fascismScore = test)
    }
}