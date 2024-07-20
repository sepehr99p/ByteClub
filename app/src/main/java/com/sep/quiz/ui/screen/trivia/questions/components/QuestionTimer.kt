package com.sep.quiz.ui.screen.trivia.questions.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

const val Delay = 1000L

enum class TimerState {
    PAUSE, RESTART, START
}

@Composable
internal fun QuestionTimer(
    modifier: Modifier = Modifier,
    timeRequired: Int = 20,
    timerState: MutableState<TimerState>,
    onTimerFinished: () -> Unit
) {
    val sliderValue = remember {
        mutableFloatStateOf(0f)
    }
    val eachStep = ((100f) / timeRequired) / 100f

    LaunchedEffect(timerState.value) {
        if (timerState.value == TimerState.RESTART) {
            sliderValue.floatValue = 0f
        }
        if (timerState.value == TimerState.PAUSE) {
            onTimerFinished.invoke()
        } else {
            while (sliderValue.floatValue != 1f) {
                delay(Delay)
                sliderValue.floatValue += eachStep
                if (sliderValue.floatValue >= 1f) {
                    onTimerFinished.invoke()
                }
            }
        }
    }
    Slider(
        modifier = modifier.fillMaxWidth(),
        value = sliderValue.floatValue,
        onValueChange = {
            sliderValue.floatValue = it
        },
        enabled = false,
        colors = SliderDefaults.colors(
            thumbColor = Color.Transparent, disabledThumbColor = Color.Transparent
        )
    )
}

@Preview
@Composable
private fun QuestionTimerPreview() {
    QuestionTimer(timerState = remember {
        mutableStateOf(TimerState.PAUSE)
    }) {

    }
}