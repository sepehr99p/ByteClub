package com.sep.byteClub.ui.screen.weather

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sep.byteClub.R
import com.sep.byteClub.domain.entity.weather.ForecastInfo
import com.sep.byteClub.domain.entity.weather.WeatherInfo
import com.sep.byteClub.ui.designSystem.components.ErrorComponent
import com.sep.byteClub.ui.designSystem.components.LoadingComponent
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_16
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_8
import com.sep.byteClub.ui.screen.weather.components.airQuality.DailyAirQualityComponent
import com.sep.byteClub.ui.screen.weather.components.airQuality.HourlyAirQualityComponent
import com.sep.byteClub.ui.screen.weather.components.forecast.daily.DailyForecast
import com.sep.byteClub.ui.screen.weather.components.forecast.hourly.HourlyForecast
import com.sep.byteClub.ui.screen.weather.components.today.Today
import com.sep.byteClub.ui.screen.weather.components.today.TodayDetails
import com.sep.byteClub.ui.utils.UiState


@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val currentWeatherState = viewModel.currentWeather.collectAsStateWithLifecycle()
    val forecastState = viewModel.forecast.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedContent(
            currentWeatherState.value,
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(500)
                ) togetherWith fadeOut(animationSpec = tween(500))
            },
            label = "Animated Content"
        ) { targetState ->
            when (targetState) {
                is UiState.Failed -> ErrorComponent(errorMessage = stringResource(id = R.string.failed_to_fetch)) {
                    viewModel.getCurrentWeather()
                }

                is UiState.Initialize -> {}
                is UiState.Loading -> LoadingComponent()
                is UiState.Success -> WeatherSuccessView(
                    currentWeatherState,
                    forecastState,
                    viewModel
                )
            }

        }

    }

}

@Composable
fun WeatherSuccessView(
    currentWeatherState: State<UiState<WeatherInfo?>>,
    forecastState: State<UiState<ForecastInfo?>>,
    viewModel: WeatherViewModel
) {
    val airQualityState = viewModel.airQuality.collectAsState()
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(padding_16))

        if (currentWeatherState.value is UiState.Success) {
            (currentWeatherState.value as UiState.Success).data!!.currentWeatherData?.let {
                Today(currentWeatherData = it)
            }
            HourlyForecast(weatherInfo = (currentWeatherState.value as UiState.Success).data!!)
            Spacer(modifier = Modifier.height(padding_8))
            DailyForecast(forecastState.value, viewModel)
        }
//        if (forecastState.value is UiState.Success) {
//            LineGraph(forecastInfo = (forecastState.value as UiState.Success).data)
//            Spacer(modifier = Modifier.height(padding_8))
//        }
//        TempPieChart(forecastInfo = forecastState.value.data)
        when (airQualityState.value) {
            is UiState.Failed -> ErrorComponent {

            }

            is UiState.Initialize -> {}
            is UiState.Loading -> LoadingComponent()
            is UiState.Success -> {
                DailyAirQualityComponent(data = (airQualityState.value as UiState.Success).data)
                HourlyAirQualityComponent(data = (airQualityState.value as UiState.Success).data)
            }
        }
        Spacer(modifier = Modifier.height(padding_8))
        if (currentWeatherState.value is UiState.Success) {
            TodayDetails((currentWeatherState.value as UiState.Success).data!!)
        }

    }

}


