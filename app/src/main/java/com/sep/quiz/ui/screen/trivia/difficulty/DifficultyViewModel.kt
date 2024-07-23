package com.sep.quiz.ui.screen.trivia.difficulty

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.quiz.domain.entiry.CategoryInfo
import com.sep.quiz.domain.usecase.quiz.CategoryInfoUseCase
import com.sep.quiz.ui.idArg
import com.sep.quiz.ui.utils.UiState
import com.sep.quiz.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DifficultyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val categoryInfoUseCase: CategoryInfoUseCase,
) : ViewModel() {

    val categoryId = MutableStateFlow(savedStateHandle.get<String>(idArg))

    private val _categoryInfo = MutableStateFlow<UiState<CategoryInfo>>(UiState.Initialize)
    val categoryInfo = _categoryInfo.asStateFlow()

    init {
        fetchCategoryInfo()
    }

    fun fetchCategoryInfo() {
        viewModelScope.launch {
            _categoryInfo.value = UiState.Loading
            when (val result = categoryInfoUseCase.invoke(categoryId.value.orEmpty())) {
                is ResultState.Exception -> {
                    _categoryInfo.value =
                        UiState.Failed(error = result.error.localizedMessage.orEmpty())
                }

                is ResultState.Failure -> {
                    _categoryInfo.value = UiState.Failed(error = result.error)
                }

                is ResultState.Success -> {
                    _categoryInfo.value = UiState.Success(data = result.data)
                }
            }
        }
    }

}