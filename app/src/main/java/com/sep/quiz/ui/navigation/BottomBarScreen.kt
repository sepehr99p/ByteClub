package com.sep.quiz.ui.navigation

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
import androidx.navigation.compose.composable
import com.sep.quiz.ui.navigation.state.BottomBarAppStatus
import com.sep.quiz.ui.screen.crypto.cryptoHomeScreen
import com.sep.quiz.ui.screen.weather.weatherScreen


@Composable
fun BottomBarScreen(
    appState: BottomBarAppStatus,
    navigateToDifficulty: (id: String) -> Unit,
    navigateToDictionary: () -> Unit
) {
    val navController = appState.navController

    var showTopBar = remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            QuizBottomBar(
                destinations = appState.navBottomDestinations,
                onNavigateToDestination = appState::navigateToBottomBarDestination,
                currentDestination = appState.currentDestination,
                needToShowTopBar = showTopBar,
                modifier = Modifier.testTag("QuizBottomBar"),
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
                homeScreen(navigateToDifficulty,navigateToDictionary)
                cryptoHomeScreen(navController)
                weatherScreen()
            }
        })

}
