package com.sep.quiz.ui.systemDesign.components.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sep.quiz.ui.systemDesign.ex.disabledColor
import com.sep.quiz.ui.systemDesign.theme.Regular_16
import com.sep.quiz.ui.systemDesign.theme.dimen.corner_8

class InvalidButtonInputException : Exception()

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    buttonStyle: ButtonStyle = ButtonStyle.Default,
    isLoading: Boolean = false,
    isDisabled: Boolean = false,
    onclick: () -> Unit,
    startIconRes: Int? = null,
    endIconRes: Int? = null,
    @StringRes titleRes: Int = 0,
    title: String = "",
    textStyle: TextStyle? = null
) {
    if (title.isEmpty() && titleRes == 0) {
        throw InvalidButtonInputException()
    }
    val containerColor = buttonStyle.getContainerColor()
    val textColor = buttonStyle.getTextColor()
    FilledButton(
        modifier,
        isLoading,
        isDisabled,
        onclick,
        startIconRes,
        endIconRes,
        titleRes,
        containerColor,
        textColor,
        title,
        textStyle
    )
}


@Composable
private fun FilledButton(
    modifier: Modifier,
    isLoading: Boolean,
    isDisabled: Boolean,
    onclick: () -> Unit,
    startIconRes: Int?,
    endIconRes: Int?,
    titleRes: Int,
    containerColor: Color,
    textColor: Color,
    title: String,
    textStyle: TextStyle? = null
) {
    Button(
        modifier = modifier,
        onClick = {
            if (isLoading.not()) {
                onclick.invoke()
            }
        },
        shape = RoundedCornerShape(corner_8),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            disabledContainerColor = containerColor.disabledColor
        ),
        enabled = !isDisabled
    ) {
        ButtonContent(
            titleRes,
            startIconRes,
            endIconRes,
            isLoading,
            isDisabled,
            textColor,
            title,
            textStyle
        )
    }
}

@Composable
private fun ButtonContent(
    titleRes: Int,
    startIconRes: Int?,
    endIconRes: Int?,
    isLoading: Boolean,
    isDisabled: Boolean,
    color: Color,
    title: String,
    textStyle: TextStyle? = null
) {
    if (isLoading) {
        LoadingComponent(color)
    } else {
        ButtonContentComponent(
            titleRes,
            isDisabled,
            color,
            title,
            textStyle
        )
    }
}

@Composable
private fun ButtonContentComponent(
    titleRes: Int,
    isDisabled: Boolean,
    color: Color,
    title: String,
    textStyle: TextStyle? = null
) {
    Text(
        style = textStyle ?: Regular_16,
        text = title.ifEmpty {
            LocalContext.current.getString(titleRes)
        },
        color = if (isDisabled) {
            color.disabledColor
        } else {
            color
        },
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun LoadingComponent(color: Color) {
    CircularProgressIndicator(
        modifier = Modifier.size(20.dp),
        color = color,
        strokeWidth = 2.dp,
        trackColor = color.copy(alpha = 0.2f)
    )
}






