package com.sep.quiz.ui.screen.difficulty

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DifficultyScreen(
    modifier: Modifier = Modifier,
    viewModel: DifficultyViewModel = hiltViewModel()
) {
    
}

@Preview
@Composable
private fun DifficultyScreenPreview(modifier: Modifier = Modifier) {
    DifficultyScreen()
}