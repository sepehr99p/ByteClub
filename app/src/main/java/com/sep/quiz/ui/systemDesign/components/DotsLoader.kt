package com.sep.quiz.ui.systemDesign.components

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

const val delayUnit = 400

@Composable
fun DotsPulsing(
    modifier: Modifier = Modifier,
    dotSize: Dp = 14.dp,
) {

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val scale1 by infiniteTransition.animateScaleWithDelay(0)
    val scale2 by infiniteTransition.animateScaleWithDelay(delayUnit)
    val scale3 by infiniteTransition.animateScaleWithDelay(delayUnit * 2)
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        val spaceSize = 2.dp
        Dot(scale = scale1, dotSize = dotSize)
        Spacer(Modifier.width(spaceSize))
        Dot(scale = scale2, dotSize = dotSize)
        Spacer(Modifier.width(spaceSize))
        Dot(scale = scale3, dotSize = dotSize)
    }
}

@Composable
fun Dot(
    scale: Float,
    dotSize: Dp = 14.dp,
) = Spacer(
    Modifier
        .size(dotSize)
        .scale(scale)
        .background(
            color = MaterialTheme.colorScheme.primary,
            shape = CircleShape
        )
)

@Composable
fun InfiniteTransition.animateScaleWithDelay(delay: Int) = this.animateFloat(
    initialValue = 0f,
    targetValue = 0f,
    animationSpec = infiniteRepeatable(
        animation = keyframes {
            durationMillis = delayUnit * 4
            0f at delay with LinearEasing
            1f at delay + delayUnit with LinearEasing
            0f at delay + delayUnit * 2
        }
    ), label = ""
)

@Preview
@Composable
private fun DotsPulsingPreview(modifier: Modifier = Modifier) {
    DotsPulsing()
}
