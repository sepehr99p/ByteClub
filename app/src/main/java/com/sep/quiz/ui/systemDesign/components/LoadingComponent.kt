package com.sep.quiz.ui.systemDesign.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingComponent(modifier: Modifier = Modifier) {
//    CircularProgressIndicator(modifier = modifier)
    Text(text = "Loading",color = MaterialTheme.colorScheme.onPrimary)
}

@Preview
@Composable
private fun LoadingComponentPreview(modifier: Modifier = Modifier) {
    LoadingComponent()
}