package com.sep.byteClub.ui.screen.weather

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val weatherRoute = "weather"

fun NavGraphBuilder.weatherScreen() {
    composable(route = weatherRoute) {
        WeatherScreen()
    }
}

fun NavController.navigateToWeather(navOptions: NavOptions? = null) {
    this.navigate(weatherRoute,navOptions)
}