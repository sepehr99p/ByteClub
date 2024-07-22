package com.sep.quiz.ui.screen.crypto.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CryptoHomeScreen(
    modifier: Modifier = Modifier,
    navigateToMarket: () -> Unit,
    navigateToTicker: () -> Unit,
    navigateToCurrency: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Crypto Home", color = MaterialTheme.colorScheme.onPrimary)
    }
}