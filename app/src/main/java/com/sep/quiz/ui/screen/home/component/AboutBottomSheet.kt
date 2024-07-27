package com.sep.quiz.ui.screen.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sep.quiz.R
import com.sep.quiz.ui.designSystem.theme.Bold_18
import com.sep.quiz.ui.designSystem.theme.Regular_10
import com.sep.quiz.ui.designSystem.theme.Regular_14
import com.sep.quiz.ui.designSystem.theme.Regular_16
import com.sep.quiz.ui.designSystem.theme.dimen.corner_24
import com.sep.quiz.ui.designSystem.theme.dimen.image_36
import com.sep.quiz.ui.designSystem.theme.dimen.padding_8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutBottomSheet(modifier: Modifier = Modifier, onDismiss: () -> Unit) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        modifier = modifier.padding(padding_8),
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
        containerColor = Color.Transparent,
        dragHandle = {},
    ) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
            modifier = Modifier.padding(padding_8),
            border = BorderStroke(width = 0.5.dp, color = Color.Black),
            shape = RoundedCornerShape(corner_24),
            colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFF232526))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding_8),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(image_36)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.logo_no_background),
                        contentDescription = "app logo",
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(padding_8))
                    Text(
                        modifier = Modifier.padding(top = padding_8),
                        text = stringResource(id = R.string.about),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = Bold_18
                    )
                }
                Text(
                    modifier = Modifier.padding(top = padding_8),
                    text = stringResource(id = R.string.api_details),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = Regular_16
                )
                Text(
                    modifier = Modifier.padding(top = padding_8),
                    text = stringResource(id = R.string.api_dictionary_details),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = Regular_16
                )
                Text(
                    modifier = Modifier.padding(top = padding_8),
                    text = stringResource(id = R.string.api_kucoin_details),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = Regular_16
                )
                Text(
                    modifier = Modifier.padding(top = padding_8),
                    text = stringResource(id = R.string.api_weather_details),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = Regular_16
                )
                Text(
                    modifier = Modifier.padding(vertical = padding_8),
                    text = stringResource(id = R.string.project_detail),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = Regular_14
                )
                Text(
                    modifier = Modifier.padding(vertical = padding_8),
                    text = "Version ${
                        LocalContext.current.packageManager.getPackageInfo(
                            LocalContext.current.packageName,
                            0
                        ).versionName
                    }",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = Regular_10,
                    fontWeight = FontWeight.Thin
                )
            }
        }
    }

}