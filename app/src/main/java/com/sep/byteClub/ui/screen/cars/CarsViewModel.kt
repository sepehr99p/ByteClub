package com.sep.byteClub.ui.screen.cars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.domain.entity.cars.CarEntity
import com.sep.byteClub.domain.usecase.cars.FetchCarsListUseCase
import com.sep.byteClub.ui.utils.UiState
import com.sep.byteClub.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val fetchCarsListUseCase: FetchCarsListUseCase
) : ViewModel() {

    private val _carListState = MutableStateFlow<UiState<List<CarEntity>>>(UiState.Initialize)
    val carListState = _carListState.asStateFlow()

    init {
        fetchCarsList()
    }

    fun fetchCarsList() {
        viewModelScope.launch {
            when (val result = fetchCarsListUseCase.invoke()) {
                is ResultState.Exception -> _carListState.emit(UiState.Failed(result.error.message.orEmpty()))
                is ResultState.Failure -> _carListState.emit(UiState.Failed(result.error))
                is ResultState.Success -> _carListState.emit(UiState.Success(result.data))
            }
        }
    }

}