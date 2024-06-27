package com.sep.quiz.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sep.quiz.ui.screen.home.HomeScreen
import com.sep.quiz.ui.systemDesign.theme.QuizTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = homeRoute
                    ) {
                        homeScreen(
                            navigateToDifficulty = {
                                navController.navigateToDifficulty(categoryId = it)
                            })
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
                    }
                }
            }
        }
    }
}