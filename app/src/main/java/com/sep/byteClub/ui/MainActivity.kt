package com.sep.byteClub.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sep.byteClub.R
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.navigation.AIRoute
import com.sep.byteClub.ui.navigation.BottomBarEntity
import com.sep.byteClub.ui.navigation.BottomBarScreen
import com.sep.byteClub.ui.navigation.BottomBarType
import com.sep.byteClub.ui.navigation.dictionaryRoute
import com.sep.byteClub.ui.navigation.f1Route
import com.sep.byteClub.ui.navigation.homeRoute
import com.sep.byteClub.ui.navigation.navigateToDifficulty
import com.sep.byteClub.ui.navigation.secretHitlerRoute
import com.sep.byteClub.ui.navigation.state.rememberBottomBarAppStatus
import com.sep.byteClub.ui.navigation.triviaScreen
import com.sep.byteClub.ui.screen.crypto.cryptoHomeRoute
import com.sep.byteClub.ui.screen.weather.weatherRoute
import com.sep.byteClub.ui.utils.GPSHelper
import com.sep.byteClub.utils.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint
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
            ByteClubTheme {
                val appState = rememberBottomBarAppStatus(
                    bottomBarList = listOf(
                        BottomBarEntity(
                            BottomBarType.CRYPTO,
                            title = stringResource(id = R.string.crypt),
                            destination = cryptoHomeRoute
                        ),
                        BottomBarEntity(
                            BottomBarType.WEATHER,
                            title = stringResource(id = R.string.weather),
                            destination = weatherRoute
                        ),
                        BottomBarEntity(
                            BottomBarType.HOME,
                            title = stringResource(id = R.string.home),
                            destination = homeRoute
                        ),
                    )
                )
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {

                    },
                    content = { innerPadding ->
                        NavHost(
                            modifier = Modifier.padding(
                                top = innerPadding.calculateTopPadding(),
                            ),
                            navController = navController,
                            startDestination = "bottom_bar"
                        ) {
                            composable("bottom_bar") {
                                BottomBarScreen(
                                    appState,
                                    navigateToDifficulty = {
                                        navController.navigateToDifficulty(
                                            categoryId = it
                                        )
                                    },
                                    navigateToDictionary = { navController.navigate(dictionaryRoute) },
                                    navigateToSecretHitler = {
                                        navController.navigate(
                                            secretHitlerRoute
                                        )
                                    },
                                    navigateToF1 = { navController.navigate(f1Route) },
                                    navigateToAI = { navController.navigate(AIRoute) }
                                )
                            }
                            triviaScreen(navController)
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
//        val geoCoder = Geocoder(this, Locale.getDefault())
    }

}