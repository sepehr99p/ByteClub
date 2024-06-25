package com.sep.quiz.ui.screen.difficulty.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.sep.quiz.domain.entiry.CategoryInfo
import com.sep.quiz.domain.entiry.QuestionDifficulty
import com.sep.quiz.ui.systemDesign.theme.Regular_14
import com.sep.quiz.ui.systemDesign.theme.dimen.corner_8
import com.sep.quiz.ui.systemDesign.theme.dimen.padding_8

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
            .padding(padding_8)
            .clip(RoundedCornerShape(corner_8))
            .padding(padding_8)
            .clickable { onClick.invoke(type) }
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