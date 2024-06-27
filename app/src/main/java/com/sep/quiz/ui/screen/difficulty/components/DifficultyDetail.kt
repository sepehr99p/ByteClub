package com.sep.quiz.ui.screen.difficulty.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.sep.quiz.domain.entiry.CategoryInfo
import com.sep.quiz.domain.entiry.QuestionDifficulty
import com.sep.quiz.ui.systemDesign.components.button.ButtonComponent
import com.sep.quiz.ui.systemDesign.theme.Regular_14
import com.sep.quiz.ui.systemDesign.theme.dimen.corner_8
import com.sep.quiz.ui.systemDesign.theme.dimen.padding_8


@Composable
internal fun DifficultyDetail(
    modifier: Modifier = Modifier,
    categoryInfo: CategoryInfo,
    onClick: (type: QuestionDifficulty, count: Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(padding_8)
    ) {
        val selectedDifficultyCount = remember {
            mutableStateOf<Int?>(null)
        }
        val selectedDifficultyType = remember {
            mutableStateOf<QuestionDifficulty?>(null)
        }

        Column(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                text = "Total ${categoryInfo.totalCount} Questions",
                style = Regular_14,
                color = MaterialTheme.colorScheme.onPrimary
            )
            DifficultyComponent(
                count = categoryInfo.easyCount,
                type = QuestionDifficulty.EASY,
                selectedDifficultyCount = selectedDifficultyCount,
                selectedDifficultyType = selectedDifficultyType
            )
            DifficultyComponent(
                count = categoryInfo.mediumCount,
                type = QuestionDifficulty.MEDIUM,
                selectedDifficultyCount = selectedDifficultyCount,
                selectedDifficultyType = selectedDifficultyType
            )
            DifficultyComponent(
                count = categoryInfo.hardCount,
                type = QuestionDifficulty.HARD,
                selectedDifficultyCount = selectedDifficultyCount,
                selectedDifficultyType = selectedDifficultyType
            )

        }

        Column(modifier = Modifier.fillMaxWidth()) {
            val finalCount = remember {
                mutableStateOf(10)
            }
            selectedDifficultyCount.value?.let { total ->
                val sliderValue = remember {
                    mutableFloatStateOf(10f / total)
                }
                finalCount.value = (sliderValue.floatValue * total).toInt()
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${finalCount.value} Questions",
                    color = MaterialTheme.colorScheme.primary
                )
                Slider(
                    modifier = Modifier.fillMaxWidth(),
                    value = sliderValue.floatValue,
                    onValueChange = {
                        sliderValue.floatValue = it
                    },
                    enabled = true
                )
            }
            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                title = "Start",
                isDisabled = selectedDifficultyCount.value == null,
                onclick = {
                    onClick.invoke(
                        selectedDifficultyType.value ?: QuestionDifficulty.EASY,
                        finalCount.value
                    )
                })
        }

    }
}


@Composable
private fun DifficultyComponent(
    modifier: Modifier = Modifier,
    count: Int,
    type: QuestionDifficulty,
    selectedDifficultyCount: MutableState<Int?>,
    selectedDifficultyType: MutableState<QuestionDifficulty?>,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = padding_8)
            .clip(RoundedCornerShape(corner_8))
            .background(
                color = if (selectedDifficultyCount.value == count) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.secondaryContainer
                }
            )
            .clickable {
                selectedDifficultyCount.value = count
                selectedDifficultyType.value = type
            }
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
    val selectedDifficulty = remember {
        mutableStateOf<Int?>(null)
    }
    val selectedDifficultyType = remember {
        mutableStateOf<QuestionDifficulty?>(null)
    }
    DifficultyComponent(
        count = 23,
        type = QuestionDifficulty.HARD,
        selectedDifficultyCount = selectedDifficulty,
        selectedDifficultyType = selectedDifficultyType
    )
}

@Preview
@Composable
private fun DifficultyDetailPreview(modifier: Modifier = Modifier) {
    DifficultyDetail(categoryInfo = mockCategoryInfo) { _, _ -> }
}