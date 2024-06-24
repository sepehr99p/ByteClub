package com.sep.quiz.ui.screen.difficulty.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sep.quiz.domain.entiry.CategoryInfo
import com.sep.quiz.ui.systemDesign.theme.Regular_14

@Composable
internal fun DifficultyDetail(modifier: Modifier = Modifier, categoryInfo: CategoryInfo) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Total ${categoryInfo.totalCount} Questions",
            style = Regular_14,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            text = "${categoryInfo.easyCount} Easy Questions",
            style = Regular_14,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            text = "${categoryInfo.mediumCount} Medium Questions",
            style = Regular_14,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            text = "${categoryInfo.hardCount} Hard Questions",
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
private fun DifficultyDetailPreview(modifier: Modifier = Modifier) {
    DifficultyDetail(categoryInfo = mockCategoryInfo)
}