package com.sep.quiz.ui.screen.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.quiz.domain.entiry.weather.AirQualityEntity
import com.sep.quiz.domain.entiry.weather.ForecastInfo
import com.sep.quiz.domain.entiry.weather.WeatherInfo
import com.sep.quiz.domain.usecase.weather.AirQualityUseCase
import com.sep.quiz.domain.usecase.weather.CurrentWeatherUseCase
import com.sep.quiz.domain.usecase.weather.ForecastWeatherUseCase
import com.sep.quiz.ui.utils.GPSHelper
import com.sep.quiz.ui.utils.UiState
import com.sep.quiz.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val forecastWeatherUseCase: ForecastWeatherUseCase,
    private val currentWeatherUseCase: CurrentWeatherUseCase,
    private val airQualityUseCase: AirQualityUseCase,
    private val gpsHelper: GPSHelper
) : ViewModel() {

    companion object {
        private const val TAG = "WeatherViewModel"
    }

    //    A CEH is optional. It should only be used when you really need to do something with unhandled exceptions.
    private val ceh = CoroutineExceptionHandler { _, t ->
        Log.e(TAG, "ceh", t)
    }




    private val scope =
        CoroutineScope(Job() + viewModelScope.coroutineContext + SupervisorJob() + ceh)

    private val _currentWeather = MutableStateFlow<UiState<WeatherInfo?>>(
        UiState.Initialize
    )
    val currentWeather: StateFlow<UiState<WeatherInfo?>> = _currentWeather

    private val _forecast = MutableStateFlow<UiState<ForecastInfo?>>(
        UiState.Initialize
    )
    val forecast: StateFlow<UiState<ForecastInfo?>> = _forecast

    private val _airQuality = MutableStateFlow<UiState<AirQualityEntity?>>(
        UiState.Initialize
    )

    val airQuality: StateFlow<UiState<AirQualityEntity?>> = _airQuality

    init {
        fetchAirQuality()
        getCurrentWeather()
        getForecast()
    }

    fun fetchAirQuality() {
        Log.i(TAG, ": ")
        scope.launch {
            _airQuality.value = UiState.Loading
            when (val result = airQualityUseCase.invoke(currentLatitude(), currentLongitude())) {
                is ResultState.Exception -> _airQuality.value =
                    UiState.Failed(result.error.message.orEmpty())

                is ResultState.Failure -> _airQuality.value = UiState.Failed(result.error)
                is ResultState.Success -> _airQuality.value = UiState.Success(result.data)
            }
        }
    }


    fun getCurrentWeather() {
        val fetchWeatherJob =
            scope.launch(CoroutineName("fetchCurrentWeather"), start = CoroutineStart.LAZY) {
                _currentWeather.value = UiState.Loading
                when (val result =
                    currentWeatherUseCase.invoke(currentLatitude(), currentLongitude())) {
                    is ResultState.Exception -> _currentWeather.value =
                        UiState.Failed(result.error.message.orEmpty())

                    is ResultState.Failure -> _currentWeather.value = UiState.Failed(result.error)
                    is ResultState.Success -> _currentWeather.value = UiState.Success(result.data)
                }
            }

        fetchWeatherJob.start()
        if (fetchWeatherJob.isCancelled) {
            throw CancellationException()
        }
    }

    fun getForecast() {
        scope.launch(CoroutineName("FetchForecast")) {
            _forecast.value = UiState.Loading
            when (val result =
                forecastWeatherUseCase.invoke(currentLatitude(), currentLongitude())) {
                is ResultState.Exception -> _forecast.value =
                    UiState.Failed(result.error.message.orEmpty())

                is ResultState.Failure -> _forecast.value = UiState.Failed(result.error)
                is ResultState.Success -> _forecast.value = UiState.Success(result.data)
            }
        }
    }

    private fun currentLongitude(): Double {
        return gpsHelper.longitude.toString().substring(
            0,
            gpsHelper.longitude.toString().length.coerceAtMost(6)
        ).toDouble()
    }

    private fun currentLatitude(): Double {
        return gpsHelper.latitude.toString().substring(
            0,
            gpsHelper.latitude.toString().length.coerceAtMost(6)
        ).toDouble()
    }


}