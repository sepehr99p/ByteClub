package com.sep.quiz.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.quiz.ui.designSystem.components.ErrorComponent
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
    navigateToDictionary: () -> Unit
) {
    val categoryState = viewModel.categories.collectAsState()
    val showCategory = remember { mutableStateOf(false) }
    val showAbout = remember { mutableStateOf(false) }
    val score = viewModel.score.collectAsState()


    Column(modifier = modifier.fillMaxSize()) {
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
                }
            )
        }
        if (showAbout.value) {
            AboutBottomSheet {
                showAbout.value = false
            }
        }
    }

}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        onCategorySelected = {},
        navigateToDictionary = {}
    )
}