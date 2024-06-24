package com.sep.quiz.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sep.quiz.ui.systemDesign.theme.SemiBold_16
import com.sep.quiz.ui.systemDesign.theme.dimen.padding_8

@Composable
fun ErrorComponent(
    modifier: Modifier = Modifier,
    errorMessage: String = "Error",
    onRetryClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8)
            .background(color = MaterialTheme.colorScheme.errorContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMessage,
            style = SemiBold_16,
            color = MaterialTheme.colorScheme.onErrorContainer
        )
        Text(
            modifier = Modifier
                .padding(vertical = padding_8)
                .clickable { onRetryClick.invoke() },
            text = "Retry",
            style = SemiBold_16,
            color = MaterialTheme.colorScheme.onErrorContainer
        )
    }
}

@Preview
@Composable
private fun ErrorComponentPreview(modifier: Modifier = Modifier) {
    ErrorComponent() {

    }
}