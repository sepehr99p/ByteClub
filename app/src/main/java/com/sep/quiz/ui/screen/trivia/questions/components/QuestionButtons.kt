package com.sep.quiz.ui.screen.trivia.questions.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sep.quiz.R
import com.sep.quiz.ui.designSystem.components.button.ButtonComponent
import com.sep.quiz.ui.designSystem.components.button.ButtonStyle
import com.sep.quiz.ui.designSystem.theme.dimen.padding_4
import com.sep.quiz.ui.designSystem.theme.dimen.padding_8

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun QuestionPagerButtons(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    answered: MutableState<Boolean>,
    timerState : MutableState<TimerState>,
    onFinish: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = padding_8)
    ) {
        val scrollNext = remember {
            mutableStateOf(false)
        }
        LaunchedEffect(answered.value) {
            if (answered.value.not()) {
                scrollNext.value = false
            }
        }
        LaunchedEffect(scrollNext.value) {
            if (scrollNext.value) {
                timerState.value = TimerState.RESTART
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                scrollNext.value = false
                answered.value = false
            }
        }
        ButtonComponent(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = padding_4),
            buttonStyle = ButtonStyle.Default,
            isDisabled = answered.value.not(),
            title = stringResource(id = R.string.next), // if this is the last question, change the title and its action
            onclick = {
                if (pagerState.currentPage == (pagerState.pageCount - 1)) {
                    onFinish.invoke()
                } else {
                    scrollNext.value = true
                }
            })

    }

}