package com.sep.quiz.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.quiz.ui.screen.components.ErrorComponent
import com.sep.quiz.ui.screen.components.LoadingComponent
import com.sep.quiz.ui.screen.home.component.CategoryList
import com.sep.quiz.ui.utils.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onCategorySelected: (id: String) -> Unit
) {
    val categoryState = viewModel.categories.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        when (categoryState.value) {
            is UiState.Failed -> {
                ErrorComponent(onRetryClick = viewModel::fetchCategories)
            }

            is UiState.Initialize -> {}
            is UiState.Loading -> {
                LoadingComponent()
            }

            is UiState.Success -> {
                CategoryList(
                    categories = (categoryState.value as UiState.Success).data,
                    onCategorySelected = onCategorySelected
                )
            }
        }
    }

}