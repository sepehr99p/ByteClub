package com.sep.byteClub.ui.screen.crypto.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sep.byteClub.R
import com.sep.byteClub.ui.designSystem.ex.shadowBackground
import com.sep.byteClub.ui.designSystem.theme.Bold_14
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_8
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_24
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_32
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_4
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8
import com.sep.byteClub.ui.screen.crypto.candles.CandlesScreen


@Composable
fun CryptoHomeScreen(
    modifier: Modifier = Modifier,
    navigateToMarket: () -> Unit,
    navigateToTicker: () -> Unit,
    navigateToCurrency: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        TickerItem(title = stringResource(id = R.string.ticker), callback = navigateToTicker)
        Row {
            HomeItems(
                modifier = Modifier.weight(1f),
                title = stringResource(id = R.string.currency),
                callback = navigateToCurrency
            )
            HomeItems(
                modifier = Modifier.weight(1f),
                title = stringResource(id = R.string.markets),
                callback = navigateToMarket
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding_8, vertical = padding_8),
            text = "BTC-USDT",
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Start,
            style = Bold_14
        )
        CandlesScreen()
    }

}

@Composable
private fun TickerItem(modifier: Modifier = Modifier, title: String, callback: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = padding_8, vertical = padding_4)
        .clip(RoundedCornerShape(corner_8))
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            callback.invoke()
        }
        .shadowBackground()
        .padding(horizontal = padding_8, vertical = padding_32)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
private fun HomeItems(modifier: Modifier = Modifier, title: String, callback: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = padding_8, vertical = padding_4)
        .clip(RoundedCornerShape(corner_8))
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            callback.invoke()
        }
        .shadowBackground()
        .padding(horizontal = padding_8, vertical = padding_24)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
private fun HomeItemsPreview() {
    HomeItems(title = "title") {}
}

@Preview
@Composable
private fun TickerItemPreview() {
    TickerItem(title = "title") {}
}