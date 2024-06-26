package com.sep.quiz.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sep.quiz.ui.screen.difficulty.DifficultyScreen
import com.sep.quiz.ui.screen.home.HomeScreen
import com.sep.quiz.ui.screen.questions.QuestionsScreen
import com.sep.quiz.ui.screen.result.ResultScreen

const val homeRoute = "home_route"
const val difficultyRoute = "difficulty_route/{id}"
const val questionsRoute = "questions_route/{id}/{difficulty}"
const val resultRoute = "result/{score}"

fun NavController.navigateToResult(score: String) {
    this.navigate(resultRoute.replace("{score}", score))
}

fun NavController.navigateToDifficulty(navOptions: NavOptions? = null, categoryId: String) {
    this.navigate(difficultyRoute.replace("{id}", categoryId), navOptions)
}

fun NavController.navigateToQuestions(
    navOptions: NavOptions? = null,
    categoryId: String,
    difficulty: String
) {
    this.navigate(
        questionsRoute.replace("{id}", categoryId).replace("{difficulty}", difficulty),
        navOptions
    )
}

fun NavController.navigateToHome() {
    this.navigate(homeRoute)
}

fun NavGraphBuilder.homeScreen(
    navigateToDifficulty: (id: String) -> Unit
) {
    composable(route = homeRoute) {
        HomeScreen(onCategorySelected = navigateToDifficulty)
    }
}

fun NavGraphBuilder.difficultyScreen(
    navigateToQuestions: (id: String, difficulty: String) -> Unit
) {
    composable(
        route = difficultyRoute,
        arguments = listOf(navArgument("id") { type = NavType.StringType })
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
            navArgument("id") { type = NavType.StringType },
            navArgument("difficulty") { type = NavType.StringType },
        )
    ) {
        QuestionsScreen(navigateToHome = navigateToHome,navigateToResult = navigateToResult)
    }
}

fun NavGraphBuilder.resultScreen(

) {
    composable(
        route = resultRoute,
        arguments = listOf(navArgument("score") { type = NavType.StringType })
    ) {
        ResultScreen()
    }
}