package com.sep.quiz.ui.designSystem.components.button

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

sealed class ButtonStyle {

    data object Default : ButtonStyle()
    data object Warning : ButtonStyle()
    data object Error : ButtonStyle()
    data object Dismiss : ButtonStyle()

}

@Composable
fun ButtonStyle.getContainerColor() : Color = when (this) {
    ButtonStyle.Default -> MaterialTheme.colorScheme.primaryContainer
    ButtonStyle.Error -> MaterialTheme.colorScheme.errorContainer
    ButtonStyle.Warning -> MaterialTheme.colorScheme.secondaryContainer
    ButtonStyle.Dismiss -> MaterialTheme.colorScheme.outlineVariant
}

@Composable
fun ButtonStyle.getTextColor() : Color = when (this) {
    ButtonStyle.Default -> MaterialTheme.colorScheme.primary
    ButtonStyle.Error -> MaterialTheme.colorScheme.error
    ButtonStyle.Warning -> MaterialTheme.colorScheme.secondary
    ButtonStyle.Dismiss -> MaterialTheme.colorScheme.onBackground
}

