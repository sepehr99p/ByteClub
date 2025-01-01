package com.sep.byteClub.ui.screen.imageToText.component

import android.Manifest
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.sep.byteClub.ui.designSystem.theme.dimen.border_1
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_24
import com.sep.byteClub.ui.designSystem.theme.dimen.image_45
import com.sep.byteClub.ui.designSystem.theme.dimen.image_8
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_16
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_24
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_32
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8
import com.sep.byteClub.ui.utils.GetPermissionType
import com.sep.byteClub.ui.utils.ThemeLocalImage
import com.sep.byteClub.ui.utils.extensions.getActivity
import com.sep.byteClub.ui.utils.openAppSettings
import com.sep.byteClub.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
internal fun ImageTypeSelectionModal(
    showTypeBottomSheet: MutableState<Boolean>,
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val cameraPermissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA,
        onPermissionResult = {
            if (it) {
                onCameraClick()
            }
        }
    )
    val galleryPermissionState = rememberPermissionState(
        permission = Manifest.permission.READ_EXTERNAL_STORAGE,
        onPermissionResult = {
            if (it) {
                onGalleryClick()
            }
        }
    )
    val showPermissionModal = remember { mutableStateOf(false) }
    var permissionToGet = remember { mutableStateOf<GetPermissionType?>(null) }
    LifecycleResumeEffect(cameraPermissionState, galleryPermissionState) {
        onPauseOrDispose {
            showPermissionModal.value = false
        }
    }
    val activity = LocalContext.current.getActivity()
    ModalBottomSheet(
        modifier = Modifier.padding(padding_8),
        onDismissRequest = {
            showTypeBottomSheet.value = false
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
            ImageTypeSelectionModalContent(
                onCameraClick = {
                    when (cameraPermissionState.status) {
                        is PermissionStatus.Denied -> {
                            permissionToGet.value = GetPermissionType.CAMERA
                            showPermissionModal.value = true
                        }

                        PermissionStatus.Granted -> onCameraClick()
                    }
                },
                onGalleryClick = {
                    when (galleryPermissionState.status) {
                        is PermissionStatus.Denied -> {
                            permissionToGet.value = GetPermissionType.GALLERY
                            showPermissionModal.value = true
                        }

                        PermissionStatus.Granted -> onGalleryClick()
                    }
                }
            )
        }
    }
    if (showPermissionModal.value) {
        ImagePermissionModal(
            showBottomSheet = showPermissionModal,
            type = permissionToGet.value ?: GetPermissionType.CAMERA,
            onGrantPermission = {
                when (permissionToGet.value) {
                    GetPermissionType.CAMERA -> {
                        when (val s = cameraPermissionState.status) {
                            is PermissionStatus.Denied -> if (s.shouldShowRationale) {
                                activity?.openAppSettings()
                            } else {
                                cameraPermissionState.launchPermissionRequest()
                            }

                            PermissionStatus.Granted -> Unit
                        }
                    }

                    GetPermissionType.GALLERY -> {
                        when (val s = galleryPermissionState.status) {
                            is PermissionStatus.Denied -> if (s.shouldShowRationale) {
                                activity?.openAppSettings()
                            } else {
                                galleryPermissionState.launchPermissionRequest()
                            }

                            PermissionStatus.Granted -> Unit
                        }
                    }

                    null -> {}
                }
            },
        )
    }
}


@Composable
private fun ImageTypeSelectionModalContent(
    modifier: Modifier = Modifier,
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageTypeSelectionModalTopLayout()
        ImageTypeSelectionModalBottomLayout(
            onCameraClick = onCameraClick,
            onGalleryClick = onGalleryClick
        )
    }
}

@Composable
private fun ImageTypeSelectionModalTopLayout(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = padding_32),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ThemeLocalImage(
            modifier = Modifier.size(image_8),
            model = R.drawable.arrow_down
        )
        Spacer(Modifier.height(padding_24))
        Text(
            text = stringResource(R.string.add_image),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
private fun ImageTypeSelectionModalBottomLayout(
    modifier: Modifier = Modifier,
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageTypeSelectionModalBottomItem(
            modifier = Modifier.weight(1f),
            iconRes = R.drawable.ic_camera_permission,
            titleRes = R.string.camera,
            onclick = onCameraClick
        )
        ImageTypeSelectionModalBottomItem(
            modifier = Modifier.weight(1f),
            iconRes = R.drawable.ic_gallary_permission,
            titleRes = R.string.gallery,
            onclick = onGalleryClick
        )
    }
}

@Composable
private fun ImageTypeSelectionModalBottomItem(
    modifier: Modifier = Modifier,
    iconRes: Int,
    titleRes: Int,
    onclick: () -> Unit,
) {
    Column(
        modifier = modifier.padding(vertical = padding_24),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = border_1,
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.onSurface
                )
                .clickable { onclick.invoke() }
                .padding(padding_16)
        ) {
            ThemeLocalImage(model = iconRes, modifier = Modifier.size(image_45))
        }
        Spacer(Modifier.height(padding_16))
        Text(
            text = stringResource(titleRes),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}


