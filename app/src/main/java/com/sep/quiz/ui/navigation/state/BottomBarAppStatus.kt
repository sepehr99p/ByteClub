package com.sep.quiz.ui.navigation.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sep.quiz.ui.navigation.BottomBarEntity
import com.sep.quiz.ui.navigation.BottomBarType
import com.sep.quiz.ui.navigation.homeRoute
import com.sep.quiz.ui.navigation.navigateToHome
import com.sep.quiz.ui.navigation.navigateToWeather
import com.sep.quiz.ui.navigation.weatherRoute
import com.sep.quiz.ui.screen.crypto.cryptoHomeRoute
import com.sep.quiz.ui.screen.crypto.navigateToCryptoHome

@Composable
fun rememberBottomBarAppStatus(
    navController: NavHostController = rememberNavController(),
    bottomBarList: List<BottomBarEntity>
): BottomBarAppStatus {
    return remember(
        navController
    ) {
        BottomBarAppStatus(navController = navController, navBottomDestinations = bottomBarList)
    }
}

@Stable
class BottomBarAppStatus(
    val navController: NavHostController,
    val navBottomDestinations: List<BottomBarEntity>
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentNBottomBarDestination: BottomBarType?
        @Composable get() = when (currentDestination?.route) {
            cryptoHomeRoute -> BottomBarType.CRYPTO
            weatherRoute -> BottomBarType.WEATHER
            homeRoute -> BottomBarType.HOME
            else -> null
        }

    fun navigateToBottomBarDestination(bottomNavBarDestination: BottomBarEntity) {
        val bottomBarNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (bottomNavBarDestination.id) {
            BottomBarType.CRYPTO -> navController.navigateToCryptoHome()
            BottomBarType.WEATHER -> navController.navigateToWeather(
                bottomBarNavOptions
            )

            BottomBarType.HOME -> navController.navigateToHome()
            BottomBarType.SETTING -> TODO()
            BottomBarType.OTHER -> TODO()
        }
    }
}
