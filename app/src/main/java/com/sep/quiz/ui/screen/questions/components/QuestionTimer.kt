package com.sep.quiz.ui.screen.questions.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
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

@Composable
internal fun QuestionTimer(
    modifier: Modifier = Modifier,
    timeRequired: Int = 20,
    stopTimer: MutableState<Boolean>,
    restartTimer: MutableState<Boolean>,
    onTimerFinished: () -> Unit
) {
    val sliderValue = remember {
        mutableFloatStateOf(0f)
    }
    val eachStep = ((100f) / timeRequired) / 100f

    LaunchedEffect(restartTimer.value) {
        if (restartTimer.value) {
            sliderValue.floatValue = 0f
            stopTimer.value = false
        }
    }

    LaunchedEffect(Unit,restartTimer.value) {
        while (sliderValue.floatValue != 1f) {
            delay(1000)
            if (stopTimer.value) {
                restartTimer.value = false
                break
            }
            sliderValue.floatValue += eachStep
            if (sliderValue.floatValue >= 1f) {
                onTimerFinished.invoke()
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
    val stopTimer = remember {
        mutableStateOf(false)
    }
    QuestionTimer(stopTimer = stopTimer, restartTimer = stopTimer) {

    }
}