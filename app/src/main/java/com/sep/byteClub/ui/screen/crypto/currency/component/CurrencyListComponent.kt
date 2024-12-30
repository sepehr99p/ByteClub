package com.sep.byteClub.ui.screen.crypto.currency.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sep.byteClub.domain.entity.crypto.CurrencyEntity

@Composable
fun CurrencyListComponent(
    modifier: Modifier = Modifier,
    currencyEntities: List<CurrencyEntity> = listOf()
) {
    LazyColumn(modifier = modifier) {
        items(currencyEntities) {
            CurrencyItemComponent(currencyEntity = it)
        }
    }
}


@Composable
fun CurrencyItemComponent(modifier: Modifier = Modifier, currencyEntity: CurrencyEntity) {
    Box(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = currencyEntity.fullName,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = currencyEntity.precision.toString(),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }

}

private val mockCurrencyEntity = CurrencyEntity(
    currency = "Currency",
    name = "name",
    fullName = "full name",
    precision = 34
)

@Preview
@Composable
private fun CurrencyItemComponentPreview() {
    CurrencyItemComponent(currencyEntity = mockCurrencyEntity)
}

@Preview
@Composable
private fun CurrencyComponentPreview() {
    CurrencyListComponent()
}