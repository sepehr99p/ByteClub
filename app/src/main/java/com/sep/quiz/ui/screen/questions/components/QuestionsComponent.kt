package com.sep.quiz.ui.screen.questions.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.sep.quiz.domain.entiry.QuestionEntity
import com.sep.quiz.ui.systemDesign.ex.ShakeConfig
import com.sep.quiz.ui.systemDesign.ex.rememberShakeController
import com.sep.quiz.ui.systemDesign.ex.shake
import com.sep.quiz.ui.systemDesign.theme.Bold_14
import com.sep.quiz.ui.systemDesign.theme.Bold_18
import com.sep.quiz.ui.systemDesign.theme.Bold_20
import com.sep.quiz.ui.systemDesign.theme.Regular_14
import com.sep.quiz.ui.systemDesign.theme.dimen.corner_8
import com.sep.quiz.ui.systemDesign.theme.dimen.padding_16
import com.sep.quiz.ui.systemDesign.theme.dimen.padding_8
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
internal fun QuestionsComponent(
    modifier: Modifier = Modifier,
    question: QuestionEntity,
    onAnswerSelected: (correct : Boolean) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        QuestionDescription(description = question.questionDescription)
        val list: ArrayList<String> = arrayListOf(question.correctAnswer)
        question.incorrectAnswers.forEach { list.add(it) }

        val userChoice = remember {
            mutableStateOf("")
        }

        LazyColumn {
            items(list.shuffled()) {
                QuestionItemComponent(
                    modifier = Modifier.padding(padding_8).clip(RoundedCornerShape(corner_8)),
                    text = it,
                    userChoice = userChoice,
                    isCorrect = it == question.correctAnswer
                ) {
                    userChoice.value = it
                    onAnswerSelected.invoke(it == question.correctAnswer)
                }
            }
        }
    }
}

@Composable
private fun QuestionItemComponent(
    modifier: Modifier = Modifier,
    text: String,
    isCorrect: Boolean,
    userChoice: MutableState<String>,
    onClick: () -> Unit
) {

    val shakeController = rememberShakeController()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (userChoice.value.isEmpty()) {
                    onClick.invoke()
                }
            }
            .background(
                color = if (userChoice.value.isEmpty()) {
                    MaterialTheme.colorScheme.tertiaryContainer
                } else {
                    if (isCorrect) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        if (userChoice.value == text) {
                            shakeController.shake(ShakeConfig(10, translateX = 5f))
                        }
                        MaterialTheme.colorScheme.errorContainer
                    }
                }
            )
            .padding(padding_8)
            .shake(shakeController)
    ) {
        Text(text = text, style = Regular_14, color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
private fun QuestionDescription(modifier: Modifier = Modifier, description: String) {
    Text(
        modifier = modifier.padding(horizontal = padding_8, vertical = padding_16),
        text = description,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        style = Bold_18
    )
}

@Preview
@Composable
private fun QuestionDescriptionPreview(modifier: Modifier = Modifier) {
    QuestionDescription(description = "description")
}

@Preview
@Composable
private fun QuestionComponentPreview(modifier: Modifier = Modifier) {
    QuestionsComponent(question = mockQuestions) {

    }
}

@Preview
@Composable
private fun QuestionItemComponentPreview(modifier: Modifier = Modifier) {
    val choice = remember {
        mutableStateOf("")
    }

    QuestionItemComponent(text = "some text", userChoice = choice, isCorrect = true) {}
}