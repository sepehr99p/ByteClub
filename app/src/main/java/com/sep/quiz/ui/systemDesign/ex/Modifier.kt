package com.sep.quiz.ui.systemDesign.ex

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush

fun Modifier.shadowBackground(): Modifier =
    composed {
        background(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primaryContainer,
                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                )
            )
        )
    }

val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()