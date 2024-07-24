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
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
//                        if (connection.value.not()) {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(top = padding_8)
//                                    .background(color = MaterialTheme.colorScheme.errorContainer)
//                            ) {
//                                Text(
//                                    modifier = Modifier
//                                        .align(Alignment.Center)
//                                        .padding(top = padding_16),
//                                    text = stringResource(id = R.string.local_mode),
//                                    color = MaterialTheme.colorScheme.error
//                                )
//                            }
//                        }
                    },
                    content = { innerPadding ->
                        NavHost(
                            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                            navController = navController,
                            startDestination = homeRoute
                        ) {
                            homeScreen(
                                navigateToDifficulty = {
                                    navController.navigateToDifficulty(categoryId = it)
                                },
                                navigateToDictionary = {
                                    navController.navigateToDictionary()
                                },
                                navigateToCrypto = navController::navigateToCryptoHome,
                                navigateToWeather = navController::navigateToWeather
                            )
                            difficultyScreen(
                                navigateToQuestions = { id, difficulty, count ->
                                    navController.navigateToQuestions(
                                        categoryId = id,
                                        difficulty = difficulty,
                                        count = count
                                    )
                                }
                            )
                            questionsScreen(
                                navigateToHome = navController::navigateToHome,
                                navigateToResult = navController::navigateToResult
                            )
                            resultScreen(navigateToHome = navController::navigateToHome)
                            dictionaryScreen()

                            cryptoHomeScreen(
                                navigateToCurrency = navController::navigateToCurrency,
                                navigateToTicker = navController::navigateToTicker,
                                navigateToMarket = navController::navigateToMarket
                            )
                            tickerScreen(
                                navigateToCandles = navController::navigateToCandles
                            )
                            currencyScreen()
                            candlesScreen()
                            marketScreen()

                            weatherScreen()

                        }
                    })
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