package com.sep.byteClub.ui.screen.imageToText

sealed interface UploadImageUiState{
    data object Initialize : UploadImageUiState
    data object Loading : UploadImageUiState
    data object Complete : UploadImageUiState
    data class Failed(val  error:String) : UploadImageUiState
    data class InProgress(val  progress: Float) : UploadImageUiState
}