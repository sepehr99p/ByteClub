package com.sep.byteClub.ui.screen.crypto.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.byteClub.R
import com.sep.byteClub.ui.designSystem.theme.Bold_20
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_16

@Composable
fun SettingsScreen() {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val selectedExchange = remember { mutableStateOf("Kucoin") }

    Column(modifier = Modifier.padding(padding_16)) {
        Text(
            text = stringResource(id = R.string.settings),
            style = Bold_20,
            color = MaterialTheme.colorScheme.onPrimary
        )
        ExchangeSelection(selectedExchange = selectedExchange, list = viewModel.exchanges)
        SecretKeyInsertion()
    }
}


@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}