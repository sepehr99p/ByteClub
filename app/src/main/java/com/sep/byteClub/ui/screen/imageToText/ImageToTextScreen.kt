package com.sep.byteClub.ui.screen.imageToText

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.byteClub.ui.designSystem.theme.Bold_20
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_16
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_16
import com.sep.byteClub.ui.screen.imageToText.component.ImageConfirmationBottomLayout
import com.sep.byteClub.ui.screen.imageToText.component.ImageTypeSelectionModal
import com.sep.byteClub.ui.utils.ComposeFileProvider
import com.sep.byteClub.ui.utils.ThemeAsyncImage

@Composable
fun ImageToTextScreen(
    modifier: Modifier = Modifier,
    viewModel: ImageToTextViewModel = hiltViewModel()
) {
    val cameraTempUri = remember { mutableStateOf<Uri?>(null) }
    val imageUri = viewModel.imageUri.collectAsState()
    val context = LocalContext.current
    val showImageSelectionModal = remember { mutableStateOf(false) }
    val openCamera = remember { mutableStateOf(false) }
    val openGallery = remember { mutableStateOf(false) }
    val uploadImageProgress = remember { mutableFloatStateOf(0f) }
    val uploadState = viewModel.imageUploadUiState.collectAsState()

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            openGallery.value = false
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                viewModel.updateUri(cameraTempUri.value)
            }
            openCamera.value = false
        }
    )

    LaunchedEffect(uploadState.value) {
        when (uploadState.value) {
            UploadImageUiState.Complete -> {
                // todo : handled completed scenario & show some result
            }

            is UploadImageUiState.Failed -> {
                // todo : handle failed scenario
            }
            is UploadImageUiState.InProgress -> uploadImageProgress.floatValue =
                (uploadState.value as UploadImageUiState.InProgress).progress

            else -> {}
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "coming soon", color = MaterialTheme.colorScheme.onPrimary, style = Bold_20)
        ThemeAsyncImage(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(corner_16)),
            model = imageUri.value.toString(),
            contentScale = ContentScale.FillBounds
        )
        Spacer(Modifier.height(padding_16))
        ImageConfirmationBottomLayout(
            modifier = Modifier,
            imageUri = imageUri,
            isUploading = uploadState.value is UploadImageUiState.Loading,
            uploadImageProgress = uploadImageProgress,
            onCancelUploadClick = {
                // todo : handle later
            },
            onStartUploadClick = {
                viewModel.submitImage()
            },
            onRetryUploadClick = {
                showImageSelectionModal.value = true
            }
        )
    }

    if (showImageSelectionModal.value) {
        ImageTypeSelectionModal(
            showTypeBottomSheet = showImageSelectionModal,
            onCameraClick = {
                showImageSelectionModal.value = false
                openCamera.value = true
            },
            onGalleryClick = {
                showImageSelectionModal.value = false
                openGallery.value = true
            }
        )
    }
    if (openCamera.value) {
        val uri = ComposeFileProvider.getImageUri(context)
        cameraTempUri.value = uri
        cameraLauncher.launch(uri)
    }

    if (openGallery.value) {
        imagePicker.launch("image/*")
    }
}