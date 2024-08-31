package com.sep.byteClub.ui.screen.secretHitler.players.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sep.byteClub.R
import com.sep.byteClub.ui.designSystem.components.button.ButtonComponent
import com.sep.byteClub.ui.designSystem.components.button.ButtonStyle
import com.sep.byteClub.ui.designSystem.theme.Medium_12
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_24
import com.sep.byteClub.ui.designSystem.theme.dimen.image_24
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_16
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddPlayerBottomSheet(
    modifier: Modifier = Modifier,
    showBottomSheet: MutableState<Boolean>,
    onPlayerAdded: (name: String) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        modifier = modifier.padding(start = padding_8, end = padding_8, bottom = padding_8),
        onDismissRequest = {
            showBottomSheet.value = false
        },
        sheetState = bottomSheetState,
        containerColor = Color.Transparent,
        dragHandle = {},
    ) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
            modifier = Modifier
                .padding(start = padding_8, end = padding_8, bottom = padding_16),
            border = BorderStroke(width = 0.5.dp, color = Color.Black),
            shape = RoundedCornerShape(corner_24),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            val playerName = remember { mutableStateOf("") }
            Column(
                modifier = Modifier.padding(horizontal = padding_16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = padding_8)
                            .height(3.dp)
                            .width(image_24)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    Text(
                        modifier = Modifier.padding(vertical = padding_8),
                        text = "Add new player",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = Medium_12
                    )
                }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = padding_8),
                    value = playerName.value,
                    onValueChange = {
                        playerName.value = it
                    }
                )
                ButtonComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = padding_8),
                    title = stringResource(id = R.string.add),
                    onclick = {
                        onPlayerAdded.invoke(playerName.value)
                        showBottomSheet.value = false
                    },
                    isDisabled = playerName.value.isEmpty(),
                    buttonStyle = ButtonStyle.Default
                )
            }
        }
    }

}
