package com.sep.quiz.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sep.quiz.ui.designSystem.ex.shimmerLoadingAnimation
import com.sep.quiz.ui.designSystem.theme.Bold_14
import com.sep.quiz.ui.designSystem.theme.dimen.corner_8
import com.sep.quiz.ui.designSystem.theme.dimen.padding_8

@Composable
fun ShimmerCategoryList() {
    LazyColumn(modifier = Modifier) {
        items(20) {
            CategoryShimmerItem()
        }
    }

}


@Composable
private fun CategoryShimmerItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(corner_8),
                spotColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f),
            )
            .clip(RoundedCornerShape(corner_8))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .shimmerLoadingAnimation()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = padding_8),
            text = "   ",
            style = Bold_14,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview
@Composable
fun CategoryShimmerItemPreview() {
    CategoryShimmerItem()
}

@Preview
@Composable
private fun ShimmerCategoryListPreview() {
    ShimmerCategoryList()
}