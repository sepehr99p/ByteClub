package com.sep.quiz.ui.screen.questions.components

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
import com.sep.quiz.ui.systemDesign.components.button.ButtonComponent
import com.sep.quiz.ui.systemDesign.components.button.ButtonStyle
import com.sep.quiz.ui.systemDesign.theme.dimen.padding_4
import com.sep.quiz.ui.systemDesign.theme.dimen.padding_8

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun QuestionPagerButtons(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    answered: MutableState<Boolean>,
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
        val scrollPrev = remember {
            mutableStateOf(false)
        }
        LaunchedEffect(answered.value) {
            if (answered.value.not()) {
                scrollNext.value = false
                scrollPrev.value = false
            }
        }
        LaunchedEffect(scrollNext.value) {
            if (scrollNext.value) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                scrollNext.value = false
            }
        }
        LaunchedEffect(scrollPrev.value) {
            if (scrollPrev.value) {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                scrollPrev.value = false
            }
        }
//        ButtonComponent(
//            modifier = Modifier
//                .weight(1f)
//                .padding(horizontal = padding_4),
//            buttonStyle = ButtonStyle.Dismiss,
//            title = "Prev",
//            isDisabled = (pagerState.currentPage == 0) || answered.value.not(), // todo : check if this is the first question
//            onclick = {
//                scrollPrev.value = true
//            })
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