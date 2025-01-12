package com.sep.byteClub.ui.screen.cars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.domain.usecase.cars.FetchCarsListUseCase
import com.sep.byteClub.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val fetchCarsListUseCase: FetchCarsListUseCase
) : ViewModel() {

    init {

    }

    fun fetchCarsList() {
        viewModelScope.launch {
            when(val result = fetchCarsListUseCase.invoke()) {
                is ResultState.Exception -> TODO()
                is ResultState.Failure -> TODO()
                is ResultState.Success -> TODO()
            }
        }
    }

}