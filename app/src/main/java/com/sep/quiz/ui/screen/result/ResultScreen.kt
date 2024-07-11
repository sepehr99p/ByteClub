package com.sep.quiz.ui.screen.result

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.quiz.R
import com.sep.quiz.ui.designSystem.theme.Bold_18
import com.sep.quiz.ui.designSystem.theme.dimen.corner_8
import com.sep.quiz.ui.designSystem.theme.dimen.padding_16

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    viewModel: ResultViewModel = hiltViewModel()
) {
    val score = viewModel.score.collectAsState()
    val oldScore = viewModel.oldScore.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = padding_16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            modifier = Modifier.padding(vertical = padding_16),
            text = stringResource(id = R.string.result),
            color = MaterialTheme.colorScheme.onPrimary,
            style = Bold_18
        )
        Text(
            modifier = Modifier.padding(vertical = padding_16),
            text = "Score : ${oldScore.value} + ${score.value}",
            style = Bold_18,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier
                .padding(vertical = padding_16)
                .clip(RoundedCornerShape(corner_8))
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(padding_16)
                .clickable { navigateToHome.invoke() },
            text = stringResource(id = R.string.go_to_home),
            style = Bold_18,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
private fun ResultScreenPreview() {
    ResultScreen(navigateToHome = {})
}