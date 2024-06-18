package com.sep.quiz.ui.systemDesign.theme.dimen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.sep.quiz.R

@Stable
val font_10: TextUnit
    @Composable
    get() = TextUnit(integerResource(id = R.integer.font10).toFloat(), TextUnitType.Sp)

@Stable
val font_12: TextUnit
    @Composable
    get() = TextUnit(integerResource(id = R.integer.font12).toFloat(), TextUnitType.Sp)

@Stable
val font_14: TextUnit
    @Composable
    get() = TextUnit(integerResource(id = R.integer.font14).toFloat(), TextUnitType.Sp)

@Stable
val font_16: TextUnit
    @Composable
    get() = TextUnit(integerResource(id = R.integer.font16).toFloat(), TextUnitType.Sp)

@Stable
val font_18: TextUnit
    @Composable
    get() = TextUnit(integerResource(id = R.integer.font18).toFloat(), TextUnitType.Sp)

@Stable
val font_20: TextUnit
    @Composable
    get() = TextUnit(integerResource(id = R.integer.font20).toFloat(), TextUnitType.Sp)
