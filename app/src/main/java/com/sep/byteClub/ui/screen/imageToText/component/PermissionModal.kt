package com.sep.byteClub.ui.screen.imageToText.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sep.byteClub.ui.designSystem.components.button.ButtonComponent
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_24
import com.sep.byteClub.ui.designSystem.theme.dimen.image_45
import com.sep.byteClub.ui.designSystem.theme.dimen.image_8
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_16
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_24
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_32
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8
import com.sep.byteClub.ui.utils.GetPermissionType
import com.sep.byteClub.R
import com.sep.byteClub.ui.utils.ThemeLocalImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ImagePermissionModal(
    showBottomSheet: MutableState<Boolean>,
    type: GetPermissionType,
    onGrantPermission: () -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        modifier = Modifier.padding(padding_8),
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
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
        ) {
            PermissionModalLayoutComponent(type = type, onGrantPermission = onGrantPermission)
        }
    }
}

@Composable
private fun PermissionModalLayoutComponent(
    modifier: Modifier = Modifier,
    type: GetPermissionType,
    onGrantPermission: () -> Unit,
) {
    Column(
        modifier = modifier.padding(padding_24),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ThemeLocalImage(
            modifier = Modifier.size(image_8),
            model = R.drawable.arrow_down
        )
        Spacer(Modifier.height(padding_24))
        Text(
            text = stringResource(type.titleRes),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Box(
            modifier = Modifier
                .padding(vertical = padding_32)
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background)
        ) {
            ThemeLocalImage(
                modifier = Modifier.size(image_45).align(Alignment.Center),
                model = type.iconRes
            )
        }

        Text(
            modifier = Modifier.padding(vertical = padding_24),
            text = stringResource(type.descriptionRes),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )

        ButtonComponent(
            modifier = Modifier.fillMaxWidth(),
            onclick = onGrantPermission,
            titleRes = R.string.grant_permission
        )
    }
}