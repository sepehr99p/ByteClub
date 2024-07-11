package com.sep.quiz.ui.screen.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.quiz.domain.usecase.score.GetScoreUseCase
import com.sep.quiz.domain.usecase.score.IncreaseScoreUseCase
import com.sep.quiz.ui.scoreArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val increaseScoreUseCase: IncreaseScoreUseCase,
    private val getScoreUseCase: GetScoreUseCase
) : ViewModel() {

    private val _score = MutableStateFlow(savedStateHandle.get<String>(scoreArg))
    val score = _score.asStateFlow()

    private val _oldScore = MutableStateFlow(0)
    val oldScore = _oldScore.asStateFlow()

    init {
        collectScore()
    }

    private fun collectScore() {
        viewModelScope.launch {
            getScoreUseCase.invoke().first {
                _oldScore.value = it.score
                updateScore()
                true
            }
        }
    }

    private fun updateScore() {
        viewModelScope.launch {
            increaseScoreUseCase.invoke(score.value?.toInt() ?: 0)
        }
    }

}