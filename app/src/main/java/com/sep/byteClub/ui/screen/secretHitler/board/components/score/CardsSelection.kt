package com.sep.byteClub.ui.screen.secretHitler.board.components.score

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerCardEntity
import com.sep.byteClub.ui.designSystem.theme.Bold_20
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.designSystem.theme.Regular_16
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_24
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_32
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8

@Composable
internal fun CardsSelection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8)
    ) {
        Text(text = "Title", color = MaterialTheme.colorScheme.onPrimary, style = Regular_16)

    }
}

@Composable
private fun PresidentCardSelection(
    modifier: Modifier = Modifier,
    onRemoveCardSelected: (card: SecretHitlerCardEntity) -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        CardItemComponent(
            modifier = Modifier.weight(1f),
            cardEntity = SecretHitlerCardEntity.FASCISM,
            onRemoveCardSelected = onRemoveCardSelected
        )
        CardItemComponent(
            modifier = Modifier.weight(1f),
            cardEntity = SecretHitlerCardEntity.FASCISM,
            onRemoveCardSelected = onRemoveCardSelected
        )
        CardItemComponent(
            modifier = Modifier.weight(1f),
            cardEntity = SecretHitlerCardEntity.FASCISM,
            onRemoveCardSelected = onRemoveCardSelected
        )
    }
}

@Composable
private fun PrimeMinisterCardSelection(
    modifier: Modifier = Modifier,
    onRemoveCardSelected: (card: SecretHitlerCardEntity) -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        CardItemComponent(
            modifier = Modifier.weight(1f),
            cardEntity = SecretHitlerCardEntity.FASCISM,
            onRemoveCardSelected = onRemoveCardSelected
        )
        CardItemComponent(
            modifier = Modifier.weight(1f),
            cardEntity = SecretHitlerCardEntity.FASCISM,
            onRemoveCardSelected = onRemoveCardSelected
        )
    }
}

@Composable
fun CardItemComponent(
    modifier: Modifier = Modifier,
    cardEntity: SecretHitlerCardEntity,
    onRemoveCardSelected: (card: SecretHitlerCardEntity) -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(corner_24))
            .background(cardEntity.color)
            .clickable { onRemoveCardSelected.invoke(cardEntity) }
            .padding(padding_32)
    ) {
        Text(
            text = cardEntity.name,
            color = MaterialTheme.colorScheme.onPrimary,
            style = Bold_20
        )
    }
}

@Preview
@Composable
private fun CardsSelectionPreview() {
    ByteClubTheme {
        CardsSelection()
    }
}