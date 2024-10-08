package com.sep.byteClub.ui.screen.weather.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_4

@Composable
internal fun CustomText(value: String?, fontSize: Int, fontWeight: FontWeight, color: Color) {
    value?.let {
        Text(
            modifier = Modifier.padding(padding_4),
            text = it,
            color = color,
            fontStyle = FontStyle.Italic,
            fontWeight = fontWeight,
            fontSize = fontSize.sp
        )
    }
}


@Preview
@Composable
fun MyViewPreview() {
    SimpleText(value = "TEST")
}


@Composable
internal fun SimpleText(modifier: Modifier = Modifier, value: String?) {
    value?.let {
        Text(
            modifier = modifier.padding(padding_4),
            text = it,
            color = MaterialTheme.colorScheme.onPrimary,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}


@Composable
internal fun OnboardingScreen(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("test")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = { shouldShowOnboarding = false }
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    OnboardingScreen()
}

