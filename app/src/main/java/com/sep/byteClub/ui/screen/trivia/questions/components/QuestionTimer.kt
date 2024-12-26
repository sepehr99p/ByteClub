package com.sep.byteClub.ui.screen.trivia.questions.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sep.byteClub.ui.screen.trivia.questions.Delay
import com.sep.byteClub.ui.screen.trivia.questions.TimerState
import kotlinx.coroutines.delay


@Composable
internal fun QuestionTimer(
    modifier: Modifier = Modifier,
    timerState: MutableState<TimerState>,
    onTimerFinished: () -> Unit
) {
    val maxTime = 30f
    var progress = remember { mutableFloatStateOf(0f) }
    val animatedProgress = animateFloatAsState(
        targetValue = (progress.floatValue / (maxTime)).toFloat(),
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        label = ""
    )

    LaunchedEffect(timerState.value) {
        when (timerState.value) {
            TimerState.PAUSE -> onTimerFinished.invoke()
            else -> {
                if (timerState.value == TimerState.RESTART) {
                    progress.floatValue = 0f
                }
                while (progress.floatValue != maxTime) {
                    delay(Delay)
                    progress.floatValue += 1f
                    if (progress.floatValue >= maxTime) {
                        onTimerFinished.invoke()
                    }
                }
            }
        }
    }

    LinearProgressIndicator(
        modifier = modifier.fillMaxWidth(),
        progress = { animatedProgress.value },
        color = Color.Red
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