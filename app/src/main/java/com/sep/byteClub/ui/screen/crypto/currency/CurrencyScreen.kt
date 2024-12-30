package com.sep.byteClub.ui.screen.crypto.currency

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.byteClub.domain.entity.crypto.CurrencyEntity
import com.sep.byteClub.ui.designSystem.components.LoadingComponent
import com.sep.byteClub.ui.screen.crypto.currency.component.CurrencyListComponent
import com.sep.byteClub.ui.utils.UiState

@Composable
fun CurrencyScreen() {
    val viewModel = hiltViewModel<CurrencyViewModel>()
    val currencyState = viewModel.currencyList.collectAsState()


    when (currencyState.value) {
        is UiState.Failed -> LoadingComponent()
        is UiState.Success -> CurrencyListComponent(currencyEntities = (currencyState.value as UiState.Success).data!!)
        is UiState.Loading -> LoadingComponent()
        is UiState.Initialize -> {}
    }
}

val mockCurrencyEntity = CurrencyEntity(
    currency = "currency",
    name = "name",
    fullName = "full name",
    precision = 24
)

@Preview
@Composable
private fun CurrencyScreenPreview() {
    CurrencyScreen()
}

@Preview
@Composable
private fun CurrencyListComponentPreview() {
    CurrencyListComponent(
        currencyEntities = listOf(
            mockCurrencyEntity,
            mockCurrencyEntity,
            mockCurrencyEntity,
            mockCurrencyEntity
        )
    )
}


