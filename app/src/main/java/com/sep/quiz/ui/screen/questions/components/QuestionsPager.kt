package com.sep.quiz.ui.screen.questions.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sep.quiz.domain.entiry.QuestionEntity
import com.sep.quiz.ui.systemDesign.theme.dimen.padding_8

@Composable
internal fun QuestionsPager(
    modifier: Modifier = Modifier,
    questions: List<QuestionEntity>) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(padding_8),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        PagerButtons()
    }
}



private val mockQuestions = QuestionEntity(
    questionDescription = "some des",
    correctAnswer = "co",
    incorrectAnswers = listOf("ro,do,vo")
)

@Preview
@Composable
private fun QuestionsPagerPreview(modifier: Modifier = Modifier) {
    QuestionsPager(questions = listOf(mockQuestions, mockQuestions, mockQuestions, mockQuestions))
}