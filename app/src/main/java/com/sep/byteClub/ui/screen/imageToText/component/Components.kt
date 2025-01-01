package com.sep.byteClub.ui.screen.imageToText.component

import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sep.byteClub.R
import com.sep.byteClub.ui.designSystem.components.button.ButtonComponent
import com.sep.byteClub.ui.designSystem.components.button.ButtonStyle
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_24
import com.sep.byteClub.ui.designSystem.theme.dimen.image_30
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_16
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8
import com.sep.byteClub.ui.utils.ThemeLocalImage


@Composable
internal fun ImageConfirmationBottomLayout(
    modifier: Modifier = Modifier,
    isUploading: Boolean,
    imageUri: State<Uri?>,
    uploadImageProgress: MutableFloatState,
    onCancelUploadClick: () -> Unit,
    onRetryUploadClick: () -> Unit,
    onStartUploadClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(corner_24))
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        if (imageUri.value == null) {
            ButtonComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding_16),
                title = stringResource(R.string.add_image),
                onclick = {
                    onRetryUploadClick.invoke()
                }
            )
        } else {
            if (isUploading) {
                ImageConfirmationUploadingComponent(onCancelUploadClick = onCancelUploadClick)
            } else {
                ImageConfirmationCheckingComponent(
                    onRetryUploadClick = onRetryUploadClick,
                    onStartUploadClick = onStartUploadClick
                )
            }
            AnimatedLinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                uploadImageProgress = uploadImageProgress,
            )
        }
    }
}

@Composable
private fun AnimatedLinearProgressIndicator(
    modifier: Modifier = Modifier,
    uploadImageProgress: MutableFloatState
) {
    val animatedProgress = animateFloatAsState(
        targetValue = uploadImageProgress.floatValue,
        animationSpec = tween(500), label = "",
    )

    LinearProgressIndicator(
        modifier = modifier.height(4.dp),
        progress = { animatedProgress.value },
        color = MaterialTheme.colorScheme.secondary,

        )
}

@Composable
private fun ImageConfirmationCheckingComponent(
    modifier: Modifier = Modifier,
    onRetryUploadClick: () -> Unit,
    onStartUploadClick: () -> Unit,
) {
    Row(modifier = modifier.padding(padding_16), verticalAlignment = Alignment.CenterVertically) {
        ButtonComponent(
            modifier = Modifier.weight(1f),
            onclick = onStartUploadClick,
            titleRes = R.string.confirm_image
        )
        RetryImageSelectionComponent(
            modifier = Modifier.weight(1f),
            onRetryUploadClick = onRetryUploadClick
        )
    }
}

@Composable
private fun RetryImageSelectionComponent(
    modifier: Modifier = Modifier,
    onRetryUploadClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable(interactionSource = interactionSource, indication = null) {
                    onRetryUploadClick.invoke()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            ThemeLocalImage(
                modifier = Modifier.size(image_30),
                model = R.drawable.ic_check
            )
            Spacer(Modifier.width(padding_8))
            Text(
                text = stringResource(R.string.retry_upload_image),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
private fun ImageConfirmationUploadingComponent(
    modifier: Modifier = Modifier,
    onCancelUploadClick: () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding_16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = stringResource(R.string.upload_image_in_progress),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            ButtonComponent(
                onclick = onCancelUploadClick,
                titleRes = R.string.cancel,
                buttonStyle = ButtonStyle.Dismiss,
            )
        }
    }
}