package com.sep.quiz.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sep.quiz.ui.screen.difficulty.DifficultyScreen
import com.sep.quiz.ui.screen.home.HomeScreen

const val homeRoute = "home_route"
const val difficultyRoute = "difficulty_route/{id}"


fun NavController.navigateToDifficulty(navOptions: NavOptions? = null, categoryId: String) {
    this.navigate(difficultyRoute.replace("{id}", categoryId), navOptions)
}

fun NavGraphBuilder.homeScreen(
    navigateToDifficulty: (id: String) -> Unit
) {
    composable(route = homeRoute) {
        HomeScreen(onCategorySelected = navigateToDifficulty)
    }
}

//difficulty_route/10

fun NavGraphBuilder.difficultyScreen() {
    composable(
        route = difficultyRoute,
        arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) {
        DifficultyScreen()
    }
}