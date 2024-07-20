package com.sep.quiz.ui.screen.trivia.difficulty

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.quiz.ui.designSystem.components.ErrorComponent
import com.sep.quiz.ui.designSystem.components.LoadingComponent
import com.sep.quiz.ui.screen.trivia.difficulty.components.DifficultyDetail
import com.sep.quiz.ui.utils.UiState

@Composable
fun DifficultyScreen(
    modifier: Modifier = Modifier,
    viewModel: DifficultyViewModel = hiltViewModel(),
    navigateToQuestions: (id: String, difficulty: String, count: Int) -> Unit
) {
    val categoryState = viewModel.categoryInfo.collectAsState()

    when (categoryState.value) {
        is UiState.Failed -> {
            ErrorComponent(
                onRetryClick = viewModel::fetchCategoryInfo,
                errorMessage = (categoryState.value as UiState.Failed).error
            )
        }

        is UiState.Initialize -> {}
        is UiState.Loading -> {
            LoadingComponent()
        }

        is UiState.Success -> {
            DifficultyDetail(
                modifier = modifier,
                categoryInfo = (categoryState.value as UiState.Success).data,
                onClick = { difficulty, count ->
                    navigateToQuestions.invoke(
                        viewModel.categoryId.value.orEmpty(),
                        difficulty.name,
                        count
                    )
                }
            )
        }
    }

}

@Preview
@Composable
private fun DifficultyScreenPreview() {
    DifficultyScreen(navigateToQuestions = { _, _, _ ->

    })
}