package com.sep.byteClub.ui.screen.cars

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.byteClub.ui.designSystem.components.ErrorComponent
import com.sep.byteClub.ui.designSystem.components.LoadingComponent
import com.sep.byteClub.ui.utils.UiState

@Composable
fun CarsScreen(modifier: Modifier = Modifier, viewModel: CarsViewModel = hiltViewModel()) {
    Column(modifier = modifier.fillMaxSize()) {
        val carListState = viewModel.carListState.collectAsState()
        when(carListState.value) {
            is UiState.Initialize -> {}
            is UiState.Failed -> ErrorComponent { viewModel.fetchCarsList() }
            is UiState.Loading -> LoadingComponent()
            is UiState.Success -> TODO()
        }
    }
}