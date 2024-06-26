package com.sep.quiz.ui.screen.questions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.quiz.ui.screen.questions.components.QuestionsPager
import com.sep.quiz.ui.systemDesign.components.ErrorComponent
import com.sep.quiz.ui.systemDesign.components.LoadingComponent
import com.sep.quiz.ui.utils.UiState

@Composable
fun QuestionsScreen(
    modifier: Modifier = Modifier,
    viewModel: QuestionViewModel = hiltViewModel()
) {
    val questionState = viewModel.questions.collectAsState()

    when(questionState.value) {
        is UiState.Failed -> {
            ErrorComponent(
                onRetryClick = viewModel::fetchQuestions
            )
        }
        is UiState.Initialize -> {}
        is UiState.Loading -> {
            LoadingComponent()
        }
        is UiState.Success -> {
            QuestionsPager(
                questions = (questionState.value as UiState.Success).data,
            )
        }
    }
}

@Preview
@Composable
private fun QuestionsScreenPreview(modifier: Modifier = Modifier) {
    QuestionsScreen()
}