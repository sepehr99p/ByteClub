package com.sep.byteClub.ui.screen.f1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sep.byteClub.ui.designSystem.theme.Bold_20

@Composable
fun F1HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "coming soon", color = MaterialTheme.colorScheme.onPrimary, style = Bold_20)
    }
}

@Preview
@Composable
private fun F1HomeScreenPreview() {

}