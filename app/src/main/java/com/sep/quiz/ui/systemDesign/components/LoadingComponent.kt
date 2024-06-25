package com.sep.quiz.ui.systemDesign.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingComponent(modifier: Modifier = Modifier) {
//    CircularProgressIndicator(modifier = modifier)
    Box(modifier = modifier.fillMaxSize()) {
        Text(modifier = Modifier.align(Alignment.Center),text = "Loading",color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Preview
@Composable
private fun LoadingComponentPreview(modifier: Modifier = Modifier) {
    LoadingComponent()
}