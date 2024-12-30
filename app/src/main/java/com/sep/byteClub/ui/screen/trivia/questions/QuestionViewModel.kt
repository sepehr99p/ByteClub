package com.sep.byteClub.ui.screen.trivia.questions

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.byteClub.domain.entity.QuestionDifficulty
import com.sep.byteClub.domain.entity.QuestionEntity
import com.sep.byteClub.domain.entity.QuestionType
import com.sep.byteClub.domain.usecase.quiz.InquiryUseCase
import com.sep.byteClub.ui.navigation.countArg
import com.sep.byteClub.ui.navigation.difficultyArg
import com.sep.byteClub.ui.navigation.idArg
import com.sep.byteClub.ui.utils.UiState
import com.sep.byteClub.utils.ResultState
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
    val categoryDifficulty = MutableStateFlow(QuestionDifficulty.valueOf(savedStateHandle.get<String>(
        difficultyArg
    ) ?: "EASY"))
    private val _count = MutableStateFlow(savedStateHandle.get<String>(countArg))

    private val _questions = MutableStateFlow<UiState<List<QuestionEntity>>>(UiState.Initialize)
    val questions = _questions.asStateFlow()


    init {
        fetchQuestions()
    }

    fun fetchQuestions() {
        viewModelScope.launch {
            _questions.value = UiState.Loading
            when (val result = inquiryUseCase.invoke(
                amount = _count.value?.toInt() ?: 10,
                difficulty = QuestionDifficulty.valueOf(categoryDifficulty.value.name),
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