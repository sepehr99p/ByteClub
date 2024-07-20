package com.sep.quiz.ui.screen.crypto.market

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.sep.quiz.ui.designSystem.ex.shadowBackground
import com.sep.quiz.ui.designSystem.theme.dimen.corner_8
import com.sep.quiz.ui.designSystem.theme.dimen.padding_16
import com.sep.quiz.ui.designSystem.theme.dimen.padding_8

@Composable
fun MarketListComponent(modifier: Modifier = Modifier, marketList: List<String>) {
//    LazyColumn(modifier = modifier) {
//        items(marketList) {
//            MarketListItemComponent(title = it)
//        }
//    }
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(3), content = {
        items(marketList) {
            MarketListItemComponent(title = it)
        }
    })
}

@Composable
fun MarketListItemComponent(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier
            .padding(padding_8)
            .clip(RoundedCornerShape(corner_8))
            .shadowBackground()
            .padding(horizontal = padding_16, vertical = padding_8),
        text = title,
        color = MaterialTheme.colorScheme.onPrimary
    )
}

@Preview
@Composable
fun MarketListItemComponentPreview() {
    MarketListItemComponent(title = "Title")
}


@Preview
@Composable
private fun MarketListComponentPreview() {
    MarketListComponent(marketList = listOf())
}