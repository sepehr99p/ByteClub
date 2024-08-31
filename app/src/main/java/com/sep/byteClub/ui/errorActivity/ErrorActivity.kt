package com.sep.byteClub.ui.errorActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import cat.ereza.customactivityoncrash.CustomActivityOnCrash.restartApplication
import com.sep.byteClub.R
import com.sep.byteClub.ui.MainActivity
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.designSystem.theme.SemiBold_14
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_16
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_32
import kotlinx.coroutines.delay

class ErrorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ByteClubTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding_16),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Image(
                        modifier = Modifier.size(120.dp),
                        painter = painterResource(id = R.drawable.ic_error),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.size(padding_32))
                    Text(
                        text = stringResource(id = R.string.crashed),
                        style = SemiBold_14.copy(textAlign = TextAlign.Center)
                    )


                    LaunchedEffect(key1 = Unit) {
                        delay(2000)
                        CustomActivityOnCrash.getConfigFromIntent(intent)?.let {
                            restartApplication(this@ErrorActivity, it)
                        }?.run {
                            MainActivity()
                            finish()
                        }

                    }
                }
            }
        }
    }
}