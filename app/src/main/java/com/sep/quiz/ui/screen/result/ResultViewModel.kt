package com.sep.quiz.ui.screen.result

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sep.quiz.ui.scoreArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _score = MutableStateFlow(savedStateHandle.get<String>(scoreArg))
    val score = _score.asStateFlow()

    init {

    }

}