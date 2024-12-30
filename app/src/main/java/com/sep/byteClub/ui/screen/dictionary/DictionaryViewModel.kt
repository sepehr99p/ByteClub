package com.sep.byteClub.ui.screen.dictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.domain.entity.dictionary.WordEntity
import com.sep.byteClub.domain.usecase.dictionary.GetWordUseCase
import com.sep.byteClub.ui.utils.UiState
import com.sep.byteClub.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    private val getWordUseCase: GetWordUseCase
) : ViewModel() {

    private val _wordDefinition = MutableStateFlow<UiState<WordEntity>>(UiState.Initialize)
    val wordDefinition = _wordDefinition.asStateFlow()

    fun fetchWord(word: String) {
        viewModelScope.launch {
            _wordDefinition.value = UiState.Loading
            when (val result = getWordUseCase.invoke(word)) {
                is ResultState.Exception -> _wordDefinition.value =
                    UiState.Failed(result.error.localizedMessage.orEmpty())

                is ResultState.Failure -> _wordDefinition.value = UiState.Failed(result.error)
                is ResultState.Success -> _wordDefinition.value = UiState.Success(result.data)
            }
        }
    }

}