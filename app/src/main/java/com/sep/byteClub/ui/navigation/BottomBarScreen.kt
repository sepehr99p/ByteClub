package com.sep.byteClub.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.sep.byteClub.ui.navigation.state.BottomBarAppStatus
import com.sep.byteClub.ui.screen.crypto.cryptoHomeScreen
import com.sep.byteClub.ui.screen.weather.weatherScreen


@Composable
fun BottomBarScreen(
    appState: BottomBarAppStatus,
    navigateToDifficulty: (id: String) -> Unit,
    navigateToSecretHitler: () -> Unit,
    navigateToDictionary: () -> Unit,
    navigateToAI: () -> Unit,
    navigateToF1: () -> Unit
) {
    val navController = appState.navController

    var showTopBar = remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            ByteClubBottomBar(
                destinations = appState.navBottomDestinations,
                onNavigateToDestination = appState::navigateToBottomBarDestination,
                currentDestination = appState.currentDestination,
                needToShowTopBar = showTopBar,
                modifier = Modifier.testTag("ByteClubBottomBar"),
            )
        },
        content = { innerPadding ->
            NavHost(
                modifier = Modifier.padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 70.dp
                ),
                navController = navController,
                startDestination = homeRoute
            ) {
                homeScreen(
                    navigateToDifficulty = navigateToDifficulty,
                    navigateToDictionary = navigateToDictionary,
                    navigateToSecretHitler = navigateToSecretHitler,
                    navigateToF1 = navigateToF1,
                    navigateToAI = navigateToAI
                )
                cryptoHomeScreen(navController)
                weatherScreen()
            }
        })

}
