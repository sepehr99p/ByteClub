package com.sep.byteClub.ui.screen.crypto.candles

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.domain.entiry.crypto.CandleEntity
import com.sep.byteClub.domain.usecase.crypto.CandlesUseCase
import com.sep.byteClub.ui.utils.UiState
import com.sep.byteClub.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CandlesViewModel @Inject constructor(
    private val candlesUseCase: CandlesUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        private const val TAG = "CandlesViewModel"
    }

    //    A CEH is optional. It should only be used when you really need to do something with unhandled exceptions.
    private val ceh = CoroutineExceptionHandler { _, t ->
        Log.e(TAG, "ceh", t)
    }

    private val scope =
        CoroutineScope(Job() + viewModelScope.coroutineContext + SupervisorJob() + ceh)

    private val _candles =
        MutableStateFlow<UiState<List<CandleEntity>?>>(UiState.Initialize)
    val candles: StateFlow<UiState<List<CandleEntity>?>> = _candles.asStateFlow()

    private val navigationParam = savedStateHandle.get<String>("symbol")

    val interval: MutableStateFlow<String> = MutableStateFlow("1day")

    val intervalList = listOf("1min","10min","30min","1hour","1day")

    init {
        fetchCandles()
    }

    fun fetchCandles(symbol: String = navigationParam ?: "BTC-USDT") {
        scope.launch {
            when (val result = candlesUseCase.invoke(interval = interval.value, symbol = symbol)) {
                is ResultState.Exception -> {
                    _candles.value = UiState.Failed(error = result.error.message ?: "error")
                }

                is ResultState.Failure -> {
                    _candles.value = UiState.Failed(error = result.error)
                }

                is ResultState.Success -> {
                    _candles.value = UiState.Success(data = result.data)
                }
            }

        }
    }


}