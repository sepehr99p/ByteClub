package com.sep.quiz.ui.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sep.quiz.R
import com.sep.quiz.ui.designSystem.theme.SemiBold_16
import com.sep.quiz.ui.designSystem.theme.dimen.corner_8
import com.sep.quiz.ui.designSystem.theme.dimen.padding_8

@Composable
fun ErrorComponent(
    modifier: Modifier = Modifier,
    errorMessage: String = stringResource(id = R.string.error),
    onRetryClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8)
            .clip(RoundedCornerShape(corner_8))
            .background(color = MaterialTheme.colorScheme.errorContainer)
            .padding(vertical = padding_8)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val interactionSource = remember {
            MutableInteractionSource()
        }
        Text(
            text = errorMessage,
            style = SemiBold_16,
            color = MaterialTheme.colorScheme.onErrorContainer
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding_8)
                .clip(RoundedCornerShape(corner_8))
                .background(color = MaterialTheme.colorScheme.error.copy(alpha = 0.1f))
                .padding(vertical = padding_8)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { onRetryClick.invoke() },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.retry),
                style = SemiBold_16,
                color = MaterialTheme.colorScheme.onErrorContainer,
                textAlign = TextAlign.Center
            )
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "retry error")
        }
    }
}

@Preview
@Composable
private fun ErrorComponentPreview() {
    ErrorComponent {

    }
}