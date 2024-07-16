package com.sep.quiz.ui.screen.dictionary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.quiz.ui.designSystem.components.ErrorComponent
import com.sep.quiz.ui.designSystem.components.LoadingComponent
import com.sep.quiz.ui.utils.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(
    modifier: Modifier = Modifier,
    viewModel: DictionaryViewModel = hiltViewModel()
) {
    val searchWord = remember { mutableStateOf("") }
    val wordDefinition = viewModel.wordDefinition.collectAsState()
    Column(modifier = modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier.fillMaxSize(),
            query = searchWord.value,
            onQueryChange = { searchWord.value = it },
            onSearch = { viewModel.fetchWord(searchWord.value) },
            active = true,
            onActiveChange = {}
        ) {
            when (wordDefinition.value) {
                is UiState.Failed -> {
                    ErrorComponent {

                    }
                }

                is UiState.Initialize -> {

                }

                is UiState.Loading -> {
                    LoadingComponent()
                }

                is UiState.Success -> {
                    Column {
                        Text(
                            text = (wordDefinition.value as UiState.Success).data.definition,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DictionaryScreenPreview() {
    DictionaryScreen()
}