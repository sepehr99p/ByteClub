package com.sep.byteClub.ui.screen.weather.components.forecast.hourly

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.sep.byteClub.ui.utils.WeatherType
import com.sep.byteClub.domain.entity.weather.WeatherData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface HourlyState {
    val time: String
    val iconRes: Int
    val temp: String
}


class HourlyUiState(private val initialValue: WeatherData) : HourlyState {

    override val time: String
        get() = getTime(initialValue.time)

    override val iconRes: Int
        get() = WeatherType.fromWMO(initialValue.weatherType).iconRes


    override val temp: String
        get() = "${initialValue.temperatureCelsius}°C"


    private fun getTime(time: LocalDateTime): String = time.format(
        DateTimeFormatter.ofPattern("HH:mm")
    )

}

@Composable
fun rememberHourlyState(weatherData: WeatherData): HourlyState = remember {
    HourlyUiState(weatherData)
}