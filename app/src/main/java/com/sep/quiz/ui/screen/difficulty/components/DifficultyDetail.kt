package com.sep.quiz.ui.screen.difficulty.components

import android.content.res.Resources.getSystem
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sep.quiz.domain.entiry.CategoryInfo
import com.sep.quiz.domain.entiry.QuestionDifficulty
import com.sep.quiz.ui.systemDesign.theme.Regular_14
import com.sep.quiz.ui.systemDesign.theme.Regular_16
import com.sep.quiz.ui.systemDesign.theme.dimen.corner_8
import com.sep.quiz.ui.systemDesign.theme.dimen.padding_8
import kotlin.math.roundToInt


@Composable
internal fun DifficultyDetail(
    modifier: Modifier = Modifier,
    categoryInfo: CategoryInfo,
    onClick: (type: QuestionDifficulty) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Total ${categoryInfo.totalCount} Questions",
            style = Regular_14,
            color = MaterialTheme.colorScheme.onPrimary
        )
        DifficultyComponent(
            count = categoryInfo.easyCount,
            type = QuestionDifficulty.EASY,
            onClick = onClick
        )
        DifficultyComponent(
            count = categoryInfo.mediumCount,
            type = QuestionDifficulty.MEDIUM,
            onClick = onClick
        )
        DifficultyComponent(
            count = categoryInfo.hardCount,
            type = QuestionDifficulty.HARD,
            onClick = onClick
        )
        DraggableNumberSelector()

    }
}

@Composable
private fun DraggableNumberSelector(modifier: Modifier = Modifier, amount : Int = 100) {
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



@Composable
private fun DifficultyComponent(
    modifier: Modifier = Modifier,
    count: Int,
    type: QuestionDifficulty,
    onClick: (type: QuestionDifficulty) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8)
            .clip(RoundedCornerShape(corner_8))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .clickable { onClick.invoke(type) }
            .padding(padding_8)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "$count ${type.name} Questions",
            style = Regular_14,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

private val mockCategoryInfo = CategoryInfo(
    totalCount = 300,
    easyCount = 24,
    mediumCount = 139,
    hardCount = 55
)

@Preview
@Composable
private fun DifficultyComponentPreview(modifier: Modifier = Modifier) {
    DifficultyComponent(count = 23, type = QuestionDifficulty.HARD) {}
}

@Preview
@Composable
private fun DifficultyDetailPreview(modifier: Modifier = Modifier) {
    DifficultyDetail(categoryInfo = mockCategoryInfo) {}
}