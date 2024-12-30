package com.sep.byteClub.ui.screen.crypto.currency

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.domain.entity.crypto.CurrencyEntity
import com.sep.byteClub.domain.usecase.crypto.CurrencyListUseCase
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
class CurrencyViewModel @Inject constructor(
    private val currencyUseCase: CurrencyListUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "CurrencyViewModel"
    }

    //    A CEH is optional. It should only be used when you really need to do something with unhandled exceptions.
    private val ceh = CoroutineExceptionHandler { _, t ->
        Log.e(TAG, "ceh", t)
    }

    private val scope =
        CoroutineScope(Job() + viewModelScope.coroutineContext + SupervisorJob() + ceh)

    private val _currencyList =
        MutableStateFlow<UiState<List<CurrencyEntity>?>>(UiState.Initialize)
    val currencyList: StateFlow<UiState<List<CurrencyEntity>?>> = _currencyList

    init {
        fetchCurrencyList()
    }

    private fun fetchCurrencyList() {
        scope.launch {
            _currencyList.value = UiState.Loading
            when (val result = currencyUseCase.invoke()) {
                is ResultState.Exception -> _currencyList.value =
                    UiState.Failed(error = result.error.message ?: "error")

                is ResultState.Failure -> _currencyList.value = UiState.Failed(error = result.error)
                is ResultState.Success -> _currencyList.value = UiState.Success(data = result.data)
            }
        }
    }


}