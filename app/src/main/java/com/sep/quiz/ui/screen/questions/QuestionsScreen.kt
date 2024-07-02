package com.sep.quiz.ui.screen.questions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.quiz.ui.screen.questions.components.QuestionsPager
import com.sep.quiz.ui.designSystem.components.ErrorComponent
import com.sep.quiz.ui.designSystem.components.LoadingComponent
import com.sep.quiz.ui.utils.UiState

@Composable
fun QuestionsScreen(
    modifier: Modifier = Modifier,
    viewModel: QuestionViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToResult : (score: String) -> Unit
) {
    val questionState = viewModel.questions.collectAsState()

    when (questionState.value) {
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
                modifier = modifier,
                questions = (questionState.value as UiState.Success).data,
                navigateToHome = navigateToHome,
                navigateToResult = {
                    navigateToResult.invoke(it.toString())
                }
            )
        }
    }
}

@Preview
@Composable
private fun QuestionsScreenPreview() {
    QuestionsScreen(navigateToHome = {}){

    }
}