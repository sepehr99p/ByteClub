package com.sep.byteClub.ui.utils

sealed interface UiState<out T> {
    data object Initialize : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Failed(val  error:String) : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
}