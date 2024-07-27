package com.sep.quiz.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.compose.NavHost
import com.sep.quiz.ui.navigation.state.BottomBarAppStatus
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


@Composable
fun BottomBarScreen(
    appState: BottomBarAppStatus,
) {
    val navController = appState.navController

    var showTopBar = remember {
        mutableStateOf(true)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

        },
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
