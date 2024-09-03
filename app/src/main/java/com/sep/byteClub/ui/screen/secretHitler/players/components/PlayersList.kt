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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.designSystem.theme.Medium_12
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_8
import com.sep.byteClub.ui.designSystem.theme.dimen.image_24
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8

@Composable
internal fun PlayersList(
    modifier: Modifier = Modifier,
    players: State<List<String>>,
    onAddPlayerClicked: () -> Unit,
    onPlayerRemoved: (name: String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(players.value) {
            PlayersItemComponent(
                name = it,
                onDeleted = {
                    onPlayerRemoved.invoke(it)
                }
            )
        }
        if (players.value.size < 10) {
            item {
                AddNewPlayerComponent(
                    onAddClicked = onAddPlayerClicked
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PlayersItemComponent(
    modifier: Modifier = Modifier,
    name: String,
    onDeleted: () -> Unit
) {
    var isRemoved by remember {
        mutableStateOf(false)
    }
    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.StartToEnd) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )
    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            onDeleted.invoke()
        }
    }
    SwipeToDismissBox(
        state = state,
        backgroundContent = {

        }, content = {
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
    )
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
                .size(image_24)
                .align(Alignment.Center),
            imageVector = Icons.Default.Add,
            contentDescription = "add new player",
            tint = MaterialTheme.colorScheme.primary
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
        PlayersItemComponent(name = "sepi", onDeleted = {})
    }
}

@Preview
@Composable
private fun PlayersListPreview() {
    ByteClubTheme {
        val test = remember { mutableStateOf(listOf("sepi", "mosi")) }
        PlayersList(players = test, onAddPlayerClicked = {}, onPlayerRemoved = {})
    }
}