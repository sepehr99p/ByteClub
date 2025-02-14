package com.sep.byteClub.data.model.weather.mapper

import com.sep.byteClub.data.model.weather.ForecastDto
import com.sep.byteClub.data.model.weather.WeatherDataDto
import com.sep.byteClub.data.model.weather.WeatherDto
import com.sep.byteClub.domain.entity.weather.ForecastInfo
import com.sep.byteClub.domain.entity.weather.WeatherData
import com.sep.byteClub.domain.entity.weather.WeatherInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = weatherCode
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

fun WeatherDto.toDomainModel(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}

fun ForecastDto.toDomainModel(): ForecastInfo = ForecastInfo(
    this.forecastDataDto.time,
    this.forecastDataDto.maxTemperatures,
    this.forecastDataDto.minTemperatures,
    this.forecastDataDto.weatherCodes,
    this.forecastDataDto.rainSum,
    this.forecastDataDto.snowfallSum
)


