package com.sep.byteClub.ui.screen.imageToText

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.data.model.ImageUploadStatus
import com.sep.byteClub.domain.usecase.ai.ImageToTextUseCase
import com.sep.byteClub.utils.ImageCompressor
import com.sep.byteClub.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageToTextViewModel @Inject constructor(
    private val imageToTextUseCase: ImageToTextUseCase,
    private val imageCompressor: ImageCompressor
) : ViewModel() {

    private var _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri = _imageUri.asStateFlow()

    private val _imageUploadUiState =
        MutableStateFlow<UploadImageUiState>(UploadImageUiState.Initialize)
    val imageUploadUiState = _imageUploadUiState.asStateFlow()

    fun submitImage() {
        viewModelScope.launch {
            _imageUri.value?.let { uri ->
                val data = imageCompressor.compressImage(
                    contentUri = uri,
                    compressionThreshold = 2
                )
                data?.let {
                    _imageUploadUiState.emit(UploadImageUiState.Loading)
                    imageToTextUseCase.invoke(data = it, imageMimType = "image/jpeg")
                        .collect { result ->
                            when (result) {
                                is ResultState.Exception -> _imageUploadUiState.emit(
                                    UploadImageUiState.Failed(result.error.message.orEmpty())
                                )

                                is ResultState.Failure -> _imageUploadUiState.emit(
                                    UploadImageUiState.Failed(result.error)
                                )

                                is ResultState.Success -> when (result.data.status) {
                                    ImageUploadStatus.Complete -> {
                                        _imageUploadUiState.emit(UploadImageUiState.Complete)
                                    }

                                    ImageUploadStatus.InProgress -> {
                                        _imageUploadUiState.emit(
                                            UploadImageUiState.InProgress(result.data.progress)
                                        )
                                    }
                                }
                            }
                        }
                }
            }
        }
    }

}