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
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import com.sep.quiz.R
import com.sep.quiz.ui.designSystem.theme.QuizTheme
import com.sep.quiz.ui.navigation.BottomBarEntity
import com.sep.quiz.ui.navigation.BottomBarScreen
import com.sep.quiz.ui.navigation.BottomBarType
import com.sep.quiz.ui.navigation.homeRoute
import com.sep.quiz.ui.navigation.state.rememberBottomBarAppStatus
import com.sep.quiz.ui.navigation.weatherRoute
import com.sep.quiz.ui.screen.crypto.cryptoHomeRoute
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
                BottomBarScreen(
                    appState = rememberBottomBarAppStatus(
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
                )
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