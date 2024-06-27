package com.sep.quiz.ui.systemDesign.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.sep.quiz.ui.systemDesign.theme.Regular_16
import com.sep.quiz.ui.systemDesign.theme.dimen.padding_8
import kotlin.math.roundToInt

@Composable
private fun DraggableNumberSelector(
    modifier: Modifier = Modifier,
    amount: Int = 100,
    selectedDifficulty: MutableState<Int?>,
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var number by remember { mutableIntStateOf(0) }
    var maxWidth by remember { mutableFloatStateOf(0f) }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(padding_8)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .onGloballyPositioned { coordinates ->
                    maxWidth = coordinates.size.width.toFloat()
                }
                .background(MaterialTheme.colorScheme.secondary)
        )

        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .clip(CircleShape)
                .size(40.dp)
                .background(MaterialTheme.colorScheme.secondary)
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        offsetX = (offsetX + delta).coerceIn(0f, maxWidth - 40.dp.value)
                        number = ((offsetX / (maxWidth - (40.dp.value))) * amount).roundToInt()
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number.toString(),
                color = MaterialTheme.colorScheme.onSecondary,
                style = Regular_16
            )
        }
    }
}
