package com.sep.byteClub.ui.screen.secretHitler.players.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.sep.byteClub.ui.designSystem.theme.Medium_12
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_8
import com.sep.byteClub.ui.designSystem.theme.dimen.image_16
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8

@Composable
internal fun PlayersList(modifier: Modifier = Modifier, players: List<String>) {
    LazyColumn(modifier = modifier) {
        items(players) {
            PlayersItemComponent(name = it)
        }
        if (players.size < 10) {
            item {
                AddNewPlayerComponent(
                    onAddClicked = {

                    }
                )
            }
        }
    }
}

@Composable
internal fun PlayersItemComponent(modifier: Modifier = Modifier, name: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8)
            .clip(RoundedCornerShape(corner_8))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(padding_8)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = name,
            style = Medium_12,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
internal fun AddNewPlayerComponent(modifier: Modifier = Modifier, onAddClicked: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8)
            .clip(RoundedCornerShape(corner_8))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { onAddClicked.invoke() }
            .padding(padding_8)
    ) {
        Icon(
            modifier = Modifier
                .size(image_16)
                .align(Alignment.Center),
            imageVector = Icons.Default.Add,
            contentDescription = "add new player"
        )
    }
}

@Preview
@Composable
private fun AddNewPlayerComponentPreview() {
    ByteClubTheme {
        AddNewPlayerComponent(onAddClicked = {})
    }
}

@Preview
@Composable
private fun PlayersItemComponentPreview() {
    ByteClubTheme {
        PlayersItemComponent(name = "sepi")
    }
}

@Preview
@Composable
private fun PlayersListPreview() {
    ByteClubTheme {
        PlayersList(players = listOf("sepi", "mosi"))
    }
}