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

const val idArg = "id"
const val difficultyArg = "difficulty"
const val countArg = "count"
const val scoreArg = "score"

const val homeRoute = "home_route"
const val difficultyRoute = "difficulty_route/{$idArg}"
const val questionsRoute = "questions_route/{$idArg}/{$difficultyArg}/{$countArg}"
const val resultRoute = "result/{$scoreArg}"
const val dictionaryRoute = "dictionary"


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

fun NavGraphBuilder.triviaScreen(
    navController: NavController
) {
    composable(
        route = difficultyRoute,
        arguments = listOf(navArgument(idArg) { type = NavType.StringType })
    ) {
        DifficultyScreen(
            navigateToQuestions = { categoryId, difficulty, count ->
                navController.navigateToQuestions(
                    categoryId = categoryId,
                    difficulty = difficulty,
                    count = count
                )
            }
        )
    }

    composable(
        route = questionsRoute,
        arguments = listOf(
            navArgument(idArg) { type = NavType.StringType },
            navArgument(difficultyArg) { type = NavType.StringType },
            navArgument(countArg) { type = NavType.StringType },
        )
    ) {
        QuestionsScreen(
            navigateToHome = navController::navigateToHome,
            navigateToResult = navController::navigateToResult
        )
    }

    composable(
        route = resultRoute,
        arguments = listOf(navArgument(scoreArg) { type = NavType.StringType })
    ) {
        ResultScreen(navigateToHome = navController::navigateToHome)
    }

    composable(route = dictionaryRoute) {
        DictionaryScreen()
    }
}

fun NavGraphBuilder.homeScreen(
    navigateToDifficulty: (id: String) -> Unit,
    navigateToDictionary: () -> Unit
) {
    composable(route = homeRoute) {
        HomeScreen(
            onCategorySelected = navigateToDifficulty,
            navigateToDictionary = navigateToDictionary,
        )
    }
}


