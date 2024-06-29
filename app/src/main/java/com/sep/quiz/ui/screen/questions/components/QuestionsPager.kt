package com.sep.quiz.ui.screen.questions.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
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
    questions: List<QuestionEntity>,
    navigateToHome: () -> Unit,
    navigateToResult: (score: Int) -> Unit
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
        val score = remember {
            mutableIntStateOf(0)
        }
        val timerState = remember {
            mutableStateOf(TimerState.START)
        }

        QuestionsHeaderComponent(
            pagerState = pagerState,
            onCloseClicked = {
                navigateToHome.invoke()
            })

        QuestionTimer(
            onTimerFinished = {
                 answered.value = true
            },
            timerState = timerState
        )


        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
            userScrollEnabled = false
        ) {
            QuestionsComponent(
                modifier = Modifier,
                question = questions[pagerState.currentPage],
                onAnswerSelected = {
                    timerState.value = TimerState.PAUSE
                    answered.value = true
                    if (it) score.intValue += 1
                })
        }
        QuestionPagerButtons(
            pagerState = pagerState,
            answered = answered,
            timerState = timerState,
            onFinish = {
                navigateToResult.invoke(score.intValue)
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
private fun QuestionsPagerPreview() {
    QuestionsPager(
        questions = listOf(mockQuestions, mockQuestions, mockQuestions, mockQuestions),
        navigateToResult = {},
        navigateToHome = {})
}