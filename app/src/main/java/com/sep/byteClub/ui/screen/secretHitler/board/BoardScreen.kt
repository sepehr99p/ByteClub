package com.sep.byteClub.ui.screen.secretHitler.board

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme

@Composable
fun BoardScreen(
    modifier: Modifier = Modifier,
    viewModel: BoardViewModel = hiltViewModel()
) {
    Column(modifier = modifier) {

    }
}

@Preview
@Composable
private fun BoardScreenPreview() {
    ByteClubTheme {
        BoardScreen()
    }
}