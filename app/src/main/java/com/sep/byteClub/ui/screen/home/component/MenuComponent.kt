package com.sep.byteClub.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sep.byteClub.R
import com.sep.byteClub.ui.designSystem.ex.bounceClick
import com.sep.byteClub.ui.designSystem.theme.Bold_20
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_8
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_16
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8

@Composable
internal fun MenuComponent(
    modifier: Modifier = Modifier,
    score: State<Int>,
    onStartClick: () -> Unit,
    onAboutClick: () -> Unit,
    navigateToDictionary: () -> Unit,
    navigateToAI: () -> Unit,
    onDadJokeClicked: () -> Unit,
    onF1Clicked: () -> Unit,
    navigateToSecretHitler: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(padding_16),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuScoreComponent(score = score)
        MenuComponentItem(title = stringResource(id = R.string.start), onClick = onStartClick)
        MenuComponentItem(title = stringResource(id = R.string.dadJoke), onClick = onDadJokeClicked)
        MenuComponentItem(title = stringResource(id = R.string.f1), onClick = onF1Clicked)
        MenuComponentItem(
            title = stringResource(id = R.string.secret_hitler),
            onClick = navigateToSecretHitler
        )
        MenuComponentItem(
            title = stringResource(id = R.string.dictionary),
            onClick = navigateToDictionary
        )
        MenuComponentItem(
            title = stringResource(id = R.string.image_to_text),
            onClick = navigateToAI
        )
        MenuComponentItem(title = stringResource(id = R.string.about), onClick = onAboutClick)
    }
}

@Composable
private fun MenuScoreComponent(modifier: Modifier = Modifier, score: State<Int>) {
    Row(
        modifier = Modifier.padding(bottom = padding_8),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = modifier.padding(padding_8),
            text = "Score : ${score.value}",
            style = Bold_20,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "score value",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun MenuComponentItem(modifier: Modifier = Modifier, title: String, onClick: () -> Unit) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = padding_8)
            .clip(RoundedCornerShape(corner_8))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .bounceClick {
                onClick.invoke()
            }
            .padding(padding_16),
        text = title,
        style = Bold_20,
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun MenuComponentItemPreview() {
    MenuComponentItem(title = "Title") {}
}

@Preview
@Composable
private fun MenuComponentPreview() {
    val score = remember { mutableIntStateOf(30) }
    MenuComponent(
        onAboutClick = {},
        onStartClick = {},
        score = score,
        navigateToDictionary = {},
        onDadJokeClicked = {},
        navigateToSecretHitler = {},
        onF1Clicked = {},
        navigateToAI = {}
    )
}

@Preview
@Composable
private fun MenuScoreComponentPreview() {
    val score = remember { mutableIntStateOf(30) }
    MenuScoreComponent(score = score)
}