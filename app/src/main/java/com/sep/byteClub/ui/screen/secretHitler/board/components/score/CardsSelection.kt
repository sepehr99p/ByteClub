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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sep.byteClub.R
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerCardEntity
import com.sep.byteClub.ui.designSystem.theme.Bold_20
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_24
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_32
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8

@Composable
internal fun CardsSelection(
    modifier: Modifier = Modifier,
    submitCard: (card: SecretHitlerCardEntity) -> Unit,
    removeCard: (card: SecretHitlerCardEntity) -> Unit,
    getCardForPresident: () -> ArrayList<SecretHitlerCardEntity>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8)
    ) {
        val startLegislation = remember { mutableStateOf(false) }
        if (startLegislation.value) {
            val cards = remember { mutableStateOf(getCardForPresident.invoke()) }
            if (cards.value.size == 3) {
                PresidentCardSelection(
                    cards = cards.value,
                    onRemoveCardSelected = {
                        cards.value.remove(it)
                        removeCard.invoke(it)
                    })
            } else {
                PrimeMinisterCardSelection(
                    cards = cards.value,
                    onRemoveCardSelected = {
                        cards.value.remove(it)
                        removeCard.invoke(it)
                        startLegislation.value = false
                        submitCard.invoke(cards.value.first())
                    })
            }

        } else {
            Text(
                modifier = Modifier.clickable { startLegislation.value = true },
                text = stringResource(id = R.string.start_legislation),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

    }
}

@Composable
private fun PresidentCardSelection(
    modifier: Modifier = Modifier,
    cards: List<SecretHitlerCardEntity>,
    onRemoveCardSelected: (card: SecretHitlerCardEntity) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.president),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            cards.forEach {
                CardItemComponent(
                    modifier = Modifier.weight(1f),
                    cardEntity = it,
                    onRemoveCardSelected = onRemoveCardSelected
                )
            }
        }

    }
}

@Composable
private fun PrimeMinisterCardSelection(
    modifier: Modifier = Modifier,
    cards: List<SecretHitlerCardEntity>,
    onRemoveCardSelected: (card: SecretHitlerCardEntity) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.prime_minister),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            cards.forEach {
                CardItemComponent(
                    modifier = Modifier.weight(1f),
                    cardEntity = it,
                    onRemoveCardSelected = onRemoveCardSelected
                )
            }
        }
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
//        CardsSelection()
    }
}