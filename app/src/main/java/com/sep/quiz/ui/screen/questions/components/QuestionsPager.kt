package com.sep.quiz.ui.screen.questions.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sep.quiz.domain.entiry.QuestionEntity
import com.sep.quiz.ui.systemDesign.theme.dimen.padding_8

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun QuestionsPager(
    modifier: Modifier = Modifier,
    questions: List<QuestionEntity>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(padding_8),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pagerState = rememberPagerState(pageCount = { questions.size })
        val answered = remember {
            mutableStateOf(false)
        }

        QuestionsHeaderComponent(
            pagerState = pagerState,
            onCloseClicked = {
                //todo : close questions and go to homepage (maybe show some dialog first to confirm)
            })
        HorizontalPager(modifier = Modifier.weight(1f),state = pagerState) {
            QuestionsComponent(
                modifier = Modifier,
                question = questions[pagerState.currentPage],
                onAnswerSelected = {
                    answered.value = true
                })
        }
        QuestionPagerButtons(
            pagerState = pagerState,
            answered = answered,
            onFinish = {
                // todo : show results
            })
    }
}


internal val mockQuestions = QuestionEntity(
    questionDescription = "some des",
    correctAnswer = "co",
    incorrectAnswers = listOf("ro,do,vo")
)

@Preview
@Composable
private fun QuestionsPagerPreview(modifier: Modifier = Modifier) {
    QuestionsPager(questions = listOf(mockQuestions, mockQuestions, mockQuestions, mockQuestions))
}