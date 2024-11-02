package com.sep.byteClub.ui.screen.secretHitler.result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.byteClub.ui.designSystem.theme.Bold_20
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_32

@Composable
internal fun SecretHitlerResultScreen(
    modifier: Modifier = Modifier,
    secretHitlerResultViewModel: SecretHitlerResultViewModel = hiltViewModel()
) {
    val winner = secretHitlerResultViewModel.winner.collectAsState()
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier.padding(vertical = padding_32),
            text = "$winner Won !",
            style = Bold_20
        )
    }
}


