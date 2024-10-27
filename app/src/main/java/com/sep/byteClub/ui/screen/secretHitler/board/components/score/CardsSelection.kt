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
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sep.byteClub.R
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerCardEntity
import com.sep.byteClub.ui.designSystem.theme.Bold_12
import com.sep.byteClub.ui.designSystem.theme.Bold_14
import com.sep.byteClub.ui.designSystem.theme.Bold_20
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.designSystem.theme.Regular_12
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_24
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_16
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_32
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun CardsSelection(
    modifier: Modifier = Modifier,
    submitCard: (card: SecretHitlerCardEntity) -> Unit,
    removeCard: (card: SecretHitlerCardEntity) -> Unit,
    getCardForPresident: () -> ArrayList<SecretHitlerCardEntity>
) {
    val startLegislation = remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8)
    ) {
        if (startLegislation.value) {
            val cards = remember { getCardForPresident.invoke().toMutableStateList()}
            if (cards.size == 3) {
                PresidentCardSelection(
                    cards = cards,
                    onRemoveCardSelected = {
                        cards.remove(it)
                        removeCard.invoke(it)
                    })
            } else {
                PrimeMinisterCardSelection(
                    cards = cards,
                    onRemoveCardSelected = {
                        cards.remove(it)
                        removeCard.invoke(it)
                        startLegislation.value = false
                        submitCard.invoke(cards.first())
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
            .padding(padding_8)
            .clip(RoundedCornerShape(corner_24))
            .background(cardEntity.color)
            .clickable { onRemoveCardSelected.invoke(cardEntity) }
            .padding(vertical = padding_32, horizontal = padding_8)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = cardEntity.name,
            color = MaterialTheme.colorScheme.onPrimary,
            style = Regular_12
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