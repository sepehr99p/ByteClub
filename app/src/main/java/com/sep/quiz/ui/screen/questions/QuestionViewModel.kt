package com.sep.quiz.ui.screen.questions

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.quiz.domain.entiry.QuestionDifficulty
import com.sep.quiz.domain.entiry.QuestionEntity
import com.sep.quiz.domain.entiry.QuestionType
import com.sep.quiz.domain.usecase.InquiryUseCase
import com.sep.quiz.ui.countArg
import com.sep.quiz.ui.difficultyArg
import com.sep.quiz.ui.idArg
import com.sep.quiz.ui.utils.UiState
import com.sep.quiz.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val inquiryUseCase: InquiryUseCase
) : ViewModel() {

    private val _categoryId = MutableStateFlow(savedStateHandle.get<String>(idArg))
    private val _categoryDifficulty = MutableStateFlow(savedStateHandle.get<String>(difficultyArg))
    private val _count = MutableStateFlow(savedStateHandle.get<String>(countArg))

    private val _questions = MutableStateFlow<UiState<List<QuestionEntity>>>(UiState.Initialize)
    val questions = _questions.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score = _score.asStateFlow()

    fun increaseScore() {
        _score.value += 1
    }



    init {
        fetchQuestions()
    }

    fun fetchQuestions() {
        viewModelScope.launch {
            _questions.value = UiState.Loading
            when (val result = inquiryUseCase.invoke(
                amount = _count.value?.toInt() ?: 10,
                difficulty = QuestionDifficulty.valueOf(_categoryDifficulty.value ?: "EASY"),
                categoryId = _categoryId.value.orEmpty(),
                type = QuestionType.MULTIPLE
            )) {
                is ResultState.Exception -> {
                    _questions.value =
                        UiState.Failed(error = result.error.localizedMessage.orEmpty())
                }

                is ResultState.Failure -> {
                    _questions.value = UiState.Failed(error = result.error)
                }

                is ResultState.Success -> {
                    _questions.value = UiState.Success(data = result.data)
                }
            }
        }
    }

}