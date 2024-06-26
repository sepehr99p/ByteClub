package com.sep.quiz.ui.screen.result

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.quiz.ui.systemDesign.theme.Bold_18

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    viewModel: ResultViewModel = hiltViewModel()
) {
    val score = viewModel.score.collectAsState()
    Box(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            text = "Result",
            color = MaterialTheme.colorScheme.onPrimary,
            style = Bold_18
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = score.value.orEmpty(),
            style = Bold_18,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
private fun ResultScreenPreview(modifier: Modifier = Modifier) {
    ResultScreen()
}