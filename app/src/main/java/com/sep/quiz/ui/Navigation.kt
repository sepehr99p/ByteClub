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

const val homeRoute = "home_route"
const val difficultyRoute = "difficulty_route/{id}"
const val questionsRoute = "questions_route/{id}/{difficulty}"


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

fun NavGraphBuilder.questionsScreen() {
    composable(
        route = questionsRoute,
        arguments = listOf(
            navArgument("id") { type = NavType.StringType },
            navArgument("difficulty") { type = NavType.StringType },
        )
    ) {
        QuestionsScreen()
    }
}