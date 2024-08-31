package com.sep.byteClub.ui.designSystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sep.byteClub.R
import com.sep.byteClub.ui.designSystem.theme.dimen.font_10
import com.sep.byteClub.ui.designSystem.theme.dimen.font_12
import com.sep.byteClub.ui.designSystem.theme.dimen.font_14
import com.sep.byteClub.ui.designSystem.theme.dimen.font_16
import com.sep.byteClub.ui.designSystem.theme.dimen.font_18
import com.sep.byteClub.ui.designSystem.theme.dimen.font_20

val font = FontFamily(
    Font(R.font.akatab, FontWeight.Thin),
)


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

private val textStyle = TextStyle(fontFamily = font)
private val Medium = textStyle.copy(fontWeight = FontWeight.W500)
private val Regular = textStyle.copy(fontWeight = FontWeight.W400)
private val SemiBold = textStyle.copy(fontWeight = FontWeight.W600)
private val Bold = textStyle.copy(fontWeight = FontWeight.W700)


val Regular_10: TextStyle
    @Composable
    get() = Regular.copy(fontSize = font_10)
val Regular_12: TextStyle
    @Composable
    get() = Regular.copy(fontSize = font_12)
val Regular_14: TextStyle
    @Composable
    get() = Regular.copy(fontSize = font_14)
val Regular_16: TextStyle
    @Composable
    get() = Regular.copy(fontSize = font_16)
val Medium_10: TextStyle
    @Composable
    get() = Medium.copy(fontSize = font_10)
val Medium_12: TextStyle
    @Composable
    get() = Medium.copy(fontSize = font_12)
val Medium_14: TextStyle
    @Composable
    get() = Medium.copy(fontSize = font_14)
val Medium_18: TextStyle
    @Composable
    get() = Medium.copy(fontSize = font_18)
val SemiBold_12
    @Composable
    get() = SemiBold.copy(fontSize = font_12)
val SemiBold_14
    @Composable
    get() = SemiBold.copy(fontSize = font_14)
val SemiBold_16: TextStyle
    @Composable
    get() = SemiBold.copy(fontSize = font_16)
val SemiBold_20: TextStyle
    @Composable
    get() = SemiBold.copy(fontSize = font_20)
val Bold_12: TextStyle
    @Composable
    get() = Bold.copy(fontSize = font_12)
val Bold_14: TextStyle
    @Composable
    get() = Bold.copy(fontSize = font_14)
val Bold_18: TextStyle
    @Composable
    get() = Bold.copy(fontSize = font_18)
val Bold_20: TextStyle
    @Composable
    get() = Bold.copy(fontSize = font_20)