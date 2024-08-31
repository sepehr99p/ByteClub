package com.sep.byteClub.ui.screen.crypto.market

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.domain.usecase.crypto.MarketListUseCase
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
class MarketViewModel @Inject constructor(
    private val marketListUseCase: MarketListUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "MarketViewModel"
    }

    //    A CEH is optional. It should only be used when you really need to do something with unhandled exceptions.
    private val ceh = CoroutineExceptionHandler { _, t ->
        Log.e(TAG, "ceh", t)
    }

    private val scope =
        CoroutineScope(Job() + viewModelScope.coroutineContext + SupervisorJob() + ceh)

    private val _marketList =
        MutableStateFlow<UiState<List<String>?>>(UiState.Initialize)
    val marketList: StateFlow<UiState<List<String>?>> = _marketList

    init {
        fetchMarketList()
    }


    private fun fetchMarketList() {
        scope.launch {
            when (val result = marketListUseCase.invoke()) {
                is ResultState.Exception -> _marketList.value =
                    UiState.Failed(result.error.message ?: "error")

                is ResultState.Failure -> _marketList.value =
                    UiState.Failed(result.error)

                is ResultState.Success -> _marketList.value = UiState.Success(result.data)
            }
        }
    }

}