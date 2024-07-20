package com.sep.quiz.ui.screen.crypto.ticker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.quiz.R
import com.sep.quiz.ui.designSystem.components.ErrorComponent
import com.sep.quiz.ui.designSystem.components.LoadingComponent
import com.sep.quiz.ui.designSystem.theme.dimen.corner_8
import com.sep.quiz.ui.designSystem.theme.dimen.padding_8
import com.sep.quiz.ui.utils.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TickerScreen(
    onTickerClicked: (symbol: String) -> Unit
) {
    val viewModel = hiltViewModel<TickerViewModel>()
    val tickerListState = viewModel.tickerList.collectAsState()
    val ticker = viewModel.ticker.collectAsState()
    val searchTicker = remember { mutableStateOf("") }
    val showSearchBar = remember { mutableStateOf(false) }
    val sortStates = listOf(
        rememberTickerSortByBidState(),
        rememberTickerSortByChangeRateState()
    )
    val selectedTickerSort = remember { mutableStateOf(sortStates[0]) }

    Column {
        if (showSearchBar.value) {
            val interactionSource = remember { MutableInteractionSource() }
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding_8)
                    .clip(RoundedCornerShape(corner_8))
                    .padding(padding_8),
                query = searchTicker.value,
                shape = RoundedCornerShape(corner_8),
                placeholder = { Text(text = stringResource(id = R.string.search_ticker_place_holder)) },
                onQueryChange = { searchTicker.value = it.uppercase() },
                onSearch = { viewModel.getTicker(searchTicker.value) },
                active = true,
                colors = SearchBarDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    dividerColor = MaterialTheme.colorScheme.primary
                ),
                onActiveChange = {
//                    showSearchBar.value = it
                },
                interactionSource = interactionSource,
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            showSearchBar.value = false
                        }, imageVector = Icons.Default.Close, contentDescription = null
                    )
                }
            ) {
                when (ticker.value) {
                    is UiState.Failed -> ErrorComponent(
                        errorMessage = stringResource(id = R.string.error_ticker_search),
                        onRetryClick = {
                            //todo : impl later
                        })

                    is UiState.Success ->
                        SingleTickerComponent(
                            singleTickerEntity = (ticker.value as UiState.Success).data!!,
                            onTickerClicked = { onTickerClicked.invoke(searchTicker.value) })

                    is UiState.Loading -> LoadingComponent()
                    is UiState.Initialize -> {

                    }
                }

            }
        }
        TickerScreenTopBar(
            callback = { showSearchBar.value = showSearchBar.value.not() }
        )
        TickerScreenSortComponent(sortList = sortStates, selectedTicker = selectedTickerSort)
        when (tickerListState.value) {
            is UiState.Failed -> ErrorComponent(errorMessage = stringResource(id = R.string.error_ticker_list)) {
                viewModel.fetchTickerList()
            }

            is UiState.Success -> TickerListComponent(
                tickerList = (tickerListState.value as UiState.Success).data!!,
                onTickerClicked = { onTickerClicked.invoke(it) },
                sortBy = { selectedTickerSort.value.sort(it) }
            )

            is UiState.Loading -> LoadingComponent()
            is UiState.Initialize -> {

            }
        }
    }


}

@Preview
@Composable
private fun TickerScreenPreview() {
    TickerScreen {}
}