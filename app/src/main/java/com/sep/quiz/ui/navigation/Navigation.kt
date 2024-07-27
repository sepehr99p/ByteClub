package com.sep.quiz.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sep.quiz.ui.screen.dictionary.DictionaryScreen
import com.sep.quiz.ui.screen.home.HomeScreen
import com.sep.quiz.ui.screen.trivia.difficulty.DifficultyScreen
import com.sep.quiz.ui.screen.trivia.questions.QuestionsScreen
import com.sep.quiz.ui.screen.trivia.result.ResultScreen
import com.sep.quiz.ui.screen.weather.WeatherScreen

const val idArg = "id"
const val difficultyArg = "difficulty"
const val countArg = "count"
const val scoreArg = "score"

const val homeRoute = "home_route"
const val difficultyRoute = "difficulty_route/{$idArg}"
const val questionsRoute = "questions_route/{$idArg}/{$difficultyArg}/{$countArg}"
const val resultRoute = "result/{$scoreArg}"
const val dictionaryRoute = "dictionary"

const val weatherRoute = "weather"


fun NavController.navigateToResult(score: String) {
    this.popBackStack(homeRoute, inclusive = false)
    this.navigate(resultRoute.replace("{$scoreArg}", score))
}

fun NavController.navigateToDifficulty(navOptions: NavOptions? = null, categoryId: String) {
    this.navigate(difficultyRoute.replace("{$idArg}", categoryId), navOptions)
}

fun NavController.navigateToQuestions(
    navOptions: NavOptions? = null,
    categoryId: String,
    difficulty: String,
    count: Int
) {
    this.navigate(
        questionsRoute.replace("{$idArg}", categoryId).replace("{$difficultyArg}", difficulty)
            .replace("{$countArg}", count.toString()),
        navOptions
    )
}

fun NavController.navigateToHome() {
    this.navigate(homeRoute)
}

fun NavGraphBuilder.homeScreen(
    navigateToDifficulty: (id: String) -> Unit,
    navigateToDictionary: () -> Unit,
    navigateToCrypto: () -> Unit,
    navigateToWeather: () -> Unit
) {
    composable(route = homeRoute) {
        HomeScreen(
            onCategorySelected = navigateToDifficulty,
            navigateToDictionary = navigateToDictionary,
            navigateToCrypto = navigateToCrypto,
            navigateToWeather = navigateToWeather
        )
    }
}

fun NavGraphBuilder.difficultyScreen(
    navigateToQuestions: (id: String, difficulty: String, count: Int) -> Unit
) {
    composable(
        route = difficultyRoute,
        arguments = listOf(navArgument(idArg) { type = NavType.StringType })
    ) {
        DifficultyScreen(navigateToQuestions = navigateToQuestions)
    }
}

fun NavGraphBuilder.questionsScreen(
    navigateToHome: () -> Unit,
    navigateToResult: (score: String) -> Unit
) {
    composable(
        route = questionsRoute,
        arguments = listOf(
            navArgument(idArg) { type = NavType.StringType },
            navArgument(difficultyArg) { type = NavType.StringType },
            navArgument(countArg) { type = NavType.StringType },
        )
    ) {
        QuestionsScreen(navigateToHome = navigateToHome, navigateToResult = navigateToResult)
    }
}

fun NavGraphBuilder.resultScreen(
    navigateToHome: () -> Unit,
) {
    composable(
        route = resultRoute,
        arguments = listOf(navArgument(scoreArg) { type = NavType.StringType })
    ) {
        ResultScreen(navigateToHome = navigateToHome)
    }
}

fun NavGraphBuilder.dictionaryScreen() {
    composable(route = dictionaryRoute) {
        DictionaryScreen()
    }
}

fun NavController.navigateToDictionary(navOptions: NavOptions? = null) {
    this.navigate(dictionaryRoute)
}

fun NavGraphBuilder.weatherScreen() {
    composable(route = weatherRoute) {
        WeatherScreen()
    }
}

fun NavController.navigateToWeather(navOptions: NavOptions? = null) {
    this.navigate(weatherRoute)
}