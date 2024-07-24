package com.sep.quiz.ui.screen.weather.components.forecast.daily

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sep.quiz.ui.screen.weather.WeatherViewModel
import com.sep.quiz.R
import com.sep.quiz.domain.entiry.weather.ForecastInfo
import com.sep.quiz.ui.designSystem.components.ErrorComponent
import com.sep.quiz.ui.designSystem.components.LoadingComponent
import com.sep.quiz.ui.utils.UiState
import com.sep.quiz.ui.utils.isNotToday

@Composable
fun ColumnScope.DailyForecast(forecast: UiState<ForecastInfo?>, viewModel: WeatherViewModel) {

    when (forecast) {
        is UiState.Failed -> {
            ErrorComponent(errorMessage = stringResource(id = R.string.failed_forecast)) {
                viewModel.getForecast()
            }
        }

        UiState.Initialize -> {}
        UiState.Loading -> {
            LoadingComponent()
        }

        is UiState.Success -> {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    , state = rememberLazyListState()
            ) {
                for (i in 0 until forecast.data!!.time.size) {
                    if (isNotToday(forecast.data.time[i])) {
                        item(key = forecast.data.time[i]) {
                            DailyForecastItem(
                                state = rememberDailyForecastState(
                                    forecastInfo = forecast.data,
                                    index = i
                                )
                            )
                        }
                    }
                }
            }

        }
    }

}


