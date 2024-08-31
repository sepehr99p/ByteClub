package com.sep.byteClub.ui.screen.crypto.ticker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.domain.entiry.crypto.SingleTickerEntity
import com.sep.byteClub.domain.entiry.crypto.TickerEntity
import com.sep.byteClub.domain.usecase.crypto.FetchTickerUseCase
import com.sep.byteClub.domain.usecase.crypto.TickerListUseCase
import com.sep.byteClub.ui.utils.UiState
import com.sep.byteClub.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerViewModel @Inject constructor(
    private val tickerUseCase: FetchTickerUseCase,
    private val tickerListUseCase: TickerListUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "TickerViewModel"
    }

    //    A CEH is optional. It should only be used when you really need to do something with unhandled exceptions.
    private val ceh = CoroutineExceptionHandler { _, t ->
        Log.e(TAG, "ceh", t)
    }

    private val scope =
        CoroutineScope(Job() + viewModelScope.coroutineContext + SupervisorJob() + ceh)


    private val _tickerList =
        MutableStateFlow<UiState<List<TickerEntity>?>>(UiState.Initialize)
    val tickerList: StateFlow<UiState<List<TickerEntity>?>> = _tickerList

    private val _ticker =
        MutableStateFlow<UiState<SingleTickerEntity?>>(UiState.Initialize)
    val ticker: StateFlow<UiState<SingleTickerEntity?>> = _ticker


    init {
        fetchTickerList()
    }

    fun getTicker(symbol: String) {
        scope.launch {
            _ticker.value = UiState.Loading
            when (val result = tickerUseCase.invoke(symbol)) {
                is ResultState.Exception -> _ticker.value =
                    UiState.Failed(result.error.message ?: "error")

                is ResultState.Failure -> _ticker.value = UiState.Failed(result.error)
                is ResultState.Success -> _ticker.value = UiState.Success(data = result.data)
            }
        }
    }


    fun fetchTickerList() {
        scope.launch {
            _tickerList.value = UiState.Loading
            when (val result = tickerListUseCase.invoke()) {
                is ResultState.Exception -> _tickerList.value =
                    UiState.Failed(result.error.message ?: "error")

                is ResultState.Failure -> _tickerList.value = UiState.Failed(result.error)
                is ResultState.Success -> _tickerList.value = UiState.Success(data = result.data)
            }
        }
    }


}