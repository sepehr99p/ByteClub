package com.sep.byteClub.ui.screen.secretHitler.players.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sep.byteClub.R
import com.sep.byteClub.ui.designSystem.components.button.ButtonComponent
import com.sep.byteClub.ui.designSystem.theme.Bold_18
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8

@Composable
internal fun PlayersHeaderComponent(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.players),
            color = MaterialTheme.colorScheme.primary,
            style = Bold_18
        )
    }
}

@Composable
internal fun StartGameBtn(
    modifier: Modifier = Modifier,
    players: SnapshotStateList<String>,
    onClick: () -> Unit
) {
    ButtonComponent(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8),
        onclick = onClick,
        isDisabled = players.size < 5,
        title = stringResource(id = R.string.start)
    )
}

@Preview
@Composable
private fun StartGameBtnPreview() {
    ByteClubTheme {
        val test = remember { mutableStateListOf("") }
        StartGameBtn(onClick = {}, players = test)
    }
}

@Preview
@Composable
private fun PlayersHeaderComponentPreview() {
    ByteClubTheme {
        PlayersHeaderComponent()
    }
}