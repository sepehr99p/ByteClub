package com.sep.byteClub.ui.screen.secretHitler.board.components.action

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerRole
import com.sep.byteClub.ui.designSystem.theme.Bold_12
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.designSystem.theme.Regular_12
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_24
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_16
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PresidentRolesWatching(
    modifier: Modifier = Modifier,
    players: List<SecretHitlerPlayerEntity>,
    showBottomSheet: MutableState<Boolean>,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        modifier = modifier.padding(start = padding_8, end = padding_8, bottom = padding_8),
        sheetState = sheetState,
        onDismissRequest = {
            showBottomSheet.value = false
        },
        dragHandle = {},
        containerColor = Color.Transparent,
    ) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
            modifier = Modifier.padding(start = padding_8, end = padding_8, bottom = padding_16),
            border = BorderStroke(width = 0.5.dp, color = Color.Black),
            shape = RoundedCornerShape(corner_24),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        ) {
            val seen = remember { mutableStateOf(false) }
            LazyColumn {
                items(players) { player ->
                    PresidentRolesWatchingItems(modifier = Modifier, player, seen)
                }
            }
        }
    }
}

@Composable
private fun PresidentRolesWatchingItems(
    modifier: Modifier = Modifier,
    player: SecretHitlerPlayerEntity,
    seen: MutableState<Boolean>,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val showRole = remember { mutableStateOf(false) }
        Text(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    if (seen.value.not()) {
                        showRole.value = true
                        seen.value = true
                    }
                },
            text = player.name,
            color = MaterialTheme.colorScheme.onPrimary,
            style = Regular_12
        )
        if (showRole.value) {
            Text(
                text = player.role.name,
                color = player.role.color,
                style = Bold_12
            )
        }
    }
}

@Preview
@Composable
private fun PresidentRolesWatchingItemsPreview() {
    ByteClubTheme {
        val test = remember { mutableStateOf(false) }
        PresidentRolesWatchingItems(
            modifier = Modifier,
            player = SecretHitlerPlayerEntity(name = "sepi", role = SecretHitlerRole.HITLER),
            seen = test
        )
    }
}