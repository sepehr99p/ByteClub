package com.sep.quiz.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.quiz.ui.designSystem.components.ErrorComponent
import com.sep.quiz.ui.designSystem.theme.Regular_14
import com.sep.quiz.ui.designSystem.theme.dimen.corner_16
import com.sep.quiz.ui.designSystem.theme.dimen.padding_16
import com.sep.quiz.ui.designSystem.theme.dimen.padding_8
import com.sep.quiz.ui.screen.home.component.AboutBottomSheet
import com.sep.quiz.ui.screen.home.component.CategoryList
import com.sep.quiz.ui.screen.home.component.MenuComponent
import com.sep.quiz.ui.screen.home.component.ShimmerCategoryList
import com.sep.quiz.ui.utils.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onCategorySelected: (id: String) -> Unit,
    navigateToDictionary: () -> Unit,
) {
    val categoryState = viewModel.categories.collectAsState()
    val showCategory = remember { mutableStateOf(false) }
    val showAbout = remember { mutableStateOf(false) }
    val score = viewModel.score.collectAsState()
    val dadJoke = viewModel.dadJoke.collectAsState()


    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (showCategory.value) {
            when (categoryState.value) {
                is UiState.Failed -> {
                    ErrorComponent(
                        onRetryClick = viewModel::fetchCategories,
                        errorMessage = (categoryState.value as UiState.Failed).error
                    )
                }

                is UiState.Initialize -> {}
                is UiState.Loading -> {
                    ShimmerCategoryList()
                }

                is UiState.Success -> {
                    CategoryList(
                        categories = (categoryState.value as UiState.Success).data,
                        onCategorySelected = onCategorySelected
                    )
                }
            }
        } else {
            MenuComponent(
                score = score,
                onStartClick = { showCategory.value = true },
                onAboutClick = { showAbout.value = true },
                navigateToDictionary = {
                    navigateToDictionary.invoke()
                },
                onDadJokeClicked = viewModel::getDadJoke
            )
        }
        if (showAbout.value) {
            AboutBottomSheet {
                showAbout.value = false
            }
        }
        if (dadJoke.value is UiState.Success) {
            Popup {
                Column(
                    modifier = modifier
                        .padding(padding_16)
                        .background(
                            shape = RoundedCornerShape(corner_16),
                            color = MaterialTheme.colorScheme.primaryContainer
                        )
                        .padding(padding_8)
                ) {
                    Text(
                        text = (dadJoke.value as UiState.Success).data.joke,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = Regular_14
                    )
                }
            }
        }
    }

}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        onCategorySelected = {},
        navigateToDictionary = {},
    )
}