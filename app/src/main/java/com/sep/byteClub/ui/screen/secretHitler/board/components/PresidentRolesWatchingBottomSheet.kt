package com.sep.byteClub.ui.screen.secretHitler.board.components

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.ui.designSystem.theme.Bold_12
import com.sep.byteClub.ui.designSystem.theme.Regular_12
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_24
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_16
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresidentRolesWatching(modifier: Modifier = Modifier, players: List<SecretHitlerPlayerEntity>) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        modifier = modifier.padding(start = padding_8, end = padding_8, bottom = padding_8),
        sheetState = sheetState,
        onDismissRequest = {

        },
        dragHandle = {},
        containerColor = Color.Transparent,
    ) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
            modifier =
            Modifier
                .padding(start = padding_8, end = padding_8, bottom = padding_16),
            border = BorderStroke(width = 0.5.dp, color = Color.Black),
            shape = RoundedCornerShape(corner_24),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        ) {
            LazyColumn {
                items(players) { player ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val showRole = remember { mutableStateOf(false) }
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { showRole.value = true },
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
            }
        }
    }
}