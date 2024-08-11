package com.sep.quiz.ui.screen.crypto

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sep.quiz.ui.navigation.homeRoute
import com.sep.quiz.ui.screen.crypto.candles.CandlesScreen
import com.sep.quiz.ui.screen.crypto.currency.CurrencyScreen
import com.sep.quiz.ui.screen.crypto.home.CryptoHomeScreen
import com.sep.quiz.ui.screen.crypto.market.MarketScreen
import com.sep.quiz.ui.screen.crypto.ticker.TickerScreen

const val cryptoHomeRoute = "cryptoHomeRoute"
const val tickerRoute = "tickerRoute"
const val candlesRoute = "candlesRoute/{symbol}"
const val marketRoute = "marketRoute"
const val currencyRoute = "currencyRoute"

fun NavGraphBuilder.cryptoHomeScreen(
    navController: NavController,
) {
    composable(route = cryptoHomeRoute) {
        CryptoHomeScreen(
            navigateToMarket = navController::navigateToMarket,
            navigateToTicker = navController::navigateToTicker,
            navigateToCurrency = navController::navigateToCurrency
        )
    }

    composable(route = tickerRoute) {
        TickerScreen(onTickerClicked = navController::navigateToCandles)
    }

    composable(
        route = candlesRoute,
        arguments = listOf(navArgument("symbol") { type = NavType.StringType })
    ) {
        CandlesScreen()
    }

    composable(route = marketRoute) {
        MarketScreen()
    }

    composable(route = currencyRoute) {
        CurrencyScreen()
    }

}

fun NavController.navigateToCryptoHome() {
    this.popBackStack(homeRoute, inclusive = false)
    this.navigate(cryptoHomeRoute)
}



fun NavController.navigateToTicker() {
    this.popBackStack(cryptoHomeRoute, inclusive = false)
    this.navigate(tickerRoute)
}


fun NavController.navigateToCandles(pair: String) {
    this.popBackStack(tickerRoute, inclusive = false)
    this.navigate(candlesRoute.replace("{symbol}", pair))
}


fun NavController.navigateToMarket() {
    this.popBackStack(cryptoHomeRoute, inclusive = false)
    this.navigate(marketRoute)
}

fun NavController.navigateToCurrency() {
    this.popBackStack(cryptoHomeRoute, inclusive = false)
    this.navigate(currencyRoute)
}
