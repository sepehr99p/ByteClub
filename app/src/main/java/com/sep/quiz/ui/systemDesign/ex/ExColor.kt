package com.sep.quiz.ui.systemDesign.ex

import androidx.compose.ui.graphics.Color
import java.lang.Exception

val Color.disabledColor: Color
    get() = this.copy(alpha = 0.3f)

val String.toColor: Color
    get() = try {
        Color(android.graphics.Color.parseColor(if (startsWith("#")) this else "#$this"))
    } catch (e: Exception) {
        Color.Yellow
    }
