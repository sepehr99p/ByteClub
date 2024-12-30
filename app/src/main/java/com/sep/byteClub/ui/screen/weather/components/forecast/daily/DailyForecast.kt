package com.sep.byteClub.ui.screen.weather.components.forecast.daily

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sep.byteClub.R
import com.sep.byteClub.domain.entity.weather.ForecastInfo
import com.sep.byteClub.ui.designSystem.components.ErrorComponent
import com.sep.byteClub.ui.designSystem.components.LoadingComponent
import com.sep.byteClub.ui.screen.weather.WeatherViewModel
import com.sep.byteClub.ui.utils.UiState
import com.sep.byteClub.ui.utils.isNotToday

@Composable
fun DailyForecast(forecast: UiState<ForecastInfo?>, viewModel: WeatherViewModel) {

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


