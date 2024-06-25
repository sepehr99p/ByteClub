package com.sep.quiz.ui.screen.questions.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sep.quiz.ui.systemDesign.components.button.ButtonComponent
import com.sep.quiz.ui.systemDesign.components.button.ButtonStyle
import com.sep.quiz.ui.systemDesign.theme.dimen.padding_8

@Composable
fun PagerButtons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = padding_8)
    ) {
        ButtonComponent(
            modifier = Modifier.weight(1f),
            buttonStyle = ButtonStyle.Default,
            title = "Next", // if this is the last question, change the title and its action
            onclick = {
                //todo : swap to next question
            })
        ButtonComponent(
            modifier = Modifier.weight(1f),
            buttonStyle = ButtonStyle.Dismiss,
            title = "Prev",
            isDisabled = false, // todo : check if this is the first question
            onclick = {
                //todo : swap to next question
            })
    }

}