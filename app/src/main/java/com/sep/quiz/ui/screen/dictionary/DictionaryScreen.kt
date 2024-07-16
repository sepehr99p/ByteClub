package com.sep.quiz.ui.screen.dictionary

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DictionaryScreen(
    modifier: Modifier = Modifier,
    viewModel: DictionaryViewModel = hiltViewModel()
) {
    Column(modifier = modifier) {
        Text(text = "Dictionary", color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Preview
@Composable
private fun DictionaryScreenPreview() {
    DictionaryScreen()
}