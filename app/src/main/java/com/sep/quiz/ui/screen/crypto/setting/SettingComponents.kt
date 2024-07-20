package com.sep.quiz.ui.screen.crypto.setting

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sep.quiz.ui.designSystem.theme.dimen.border_2
import com.sep.quiz.ui.designSystem.theme.dimen.corner_16
import com.sep.quiz.ui.designSystem.theme.dimen.padding_8

@Composable
fun ExchangeSelection(
    modifier: Modifier = Modifier,
    selectedExchange: MutableState<String>,
    list: List<String>
) {
    Box(modifier = modifier.fillMaxWidth()) {
        val expanded = remember {
            mutableStateOf(false)
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .padding(padding_8)
                .border(
                    width = border_2,
                    shape = RoundedCornerShape(corner_16),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                .padding(padding_8)
                .clickable {
                    expanded.value = true
                },
            text = selectedExchange.value,
            color = MaterialTheme.colorScheme.onPrimary
        )
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }) {
            list.forEach {
                DropdownMenuItem(text = { Text(text = it.uppercase()) }, onClick = {
                    selectedExchange.value = it
                    expanded.value = false
                })
            }
        }
    }
}

@Preview
@Composable
private fun ExchangeSelectionPreview() {
    val selected = remember { mutableStateOf("kucoin") }
    val list = listOf("Binance", "kucoin")
    ExchangeSelection(selectedExchange = selected, list = list)
}

@Composable
fun ApiKeyInsertion() {

}

@Preview
@Composable
private fun ApiKeyInsertionPreview() {
    ApiKeyInsertion()
}

@Composable
fun SecretKeyInsertion() {

}

@Preview
@Composable
private fun SecretKeyInsertionPreview() {
    SecretKeyInsertion()
}