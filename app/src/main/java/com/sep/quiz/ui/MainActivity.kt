package com.sep.quiz.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sep.quiz.ui.designSystem.theme.QuizTheme
import com.sep.quiz.ui.navigation.BottomBarEntity
import com.sep.quiz.ui.navigation.BottomBarScreen
import com.sep.quiz.ui.navigation.BottomBarType
import com.sep.quiz.ui.navigation.dictionaryScreen
import com.sep.quiz.ui.navigation.difficultyScreen
import com.sep.quiz.ui.navigation.homeRoute
import com.sep.quiz.ui.navigation.homeScreen
import com.sep.quiz.ui.navigation.navigateToDictionary
import com.sep.quiz.ui.navigation.navigateToDifficulty
import com.sep.quiz.ui.navigation.navigateToHome
import com.sep.quiz.ui.navigation.navigateToQuestions
import com.sep.quiz.ui.navigation.navigateToResult
import com.sep.quiz.ui.navigation.navigateToWeather
import com.sep.quiz.ui.navigation.questionsScreen
import com.sep.quiz.ui.navigation.resultScreen
import com.sep.quiz.ui.navigation.state.rememberBottomBarAppStatus
import com.sep.quiz.ui.navigation.weatherScreen
import com.sep.quiz.ui.screen.crypto.candlesScreen
import com.sep.quiz.ui.screen.crypto.cryptoHomeScreen
import com.sep.quiz.ui.screen.crypto.currencyScreen
import com.sep.quiz.ui.screen.crypto.marketScreen
import com.sep.quiz.ui.screen.crypto.navigateToCandles
import com.sep.quiz.ui.screen.crypto.navigateToCryptoHome
import com.sep.quiz.ui.screen.crypto.navigateToCurrency
import com.sep.quiz.ui.screen.crypto.navigateToMarket
import com.sep.quiz.ui.screen.crypto.navigateToTicker
import com.sep.quiz.ui.screen.crypto.tickerScreen
import com.sep.quiz.ui.utils.GPSHelper
import com.sep.quiz.utils.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), LocationListener {

    @Inject
    lateinit var networkConnection: NetworkConnection

    @Inject
    lateinit var gpsHelper: GPSHelper

    private var latitude = 0.0
    private var longitude = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            QuizTheme {
                val navController = rememberNavController()
                val connection = remember {
                    mutableStateOf(networkConnection.isInternetOn())
                }
                BottomBarScreen(appState = rememberBottomBarAppStatus(bottomBarList = listOf(
                    BottomBarEntity(BottomBarType.CRYPTO, title = "crypto", destination = "crypto"),
                    BottomBarEntity(BottomBarType.WEATHER, title = "weather", destination = "weather"),
                    BottomBarEntity(BottomBarType.GAME, title = "game", destination = "game"),
                )))
            }
        }
    }

    private fun getLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 102
            )
            return
        } else {
            updateLocation()
        }
    }

    private fun updateLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 102
            )
            //
        } else {
            gpsHelper.getMyLocation()
        }
    }

    override fun onLocationChanged(location: Location) {
        latitude = location.latitude
        longitude = location.longitude
        val geoCoder = Geocoder(this, Locale.getDefault())
    }

}