package com.sep.quiz.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sep.quiz.R
import com.sep.quiz.ui.designSystem.theme.QuizTheme
import com.sep.quiz.ui.designSystem.theme.dimen.padding_16
import com.sep.quiz.ui.designSystem.theme.dimen.padding_4
import com.sep.quiz.ui.designSystem.theme.dimen.padding_8
import com.sep.quiz.utils.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkConnection: NetworkConnection
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizTheme {
                val navController = rememberNavController()
                val connection = remember {
                    mutableStateOf(networkConnection.isInternetOn())
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
//                        if (connection.value.not()) {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(top = padding_8)
//                                    .background(color = MaterialTheme.colorScheme.errorContainer)
//                            ) {
//                                Text(
//                                    modifier = Modifier
//                                        .align(Alignment.Center)
//                                        .padding(top = padding_16),
//                                    text = stringResource(id = R.string.local_mode),
//                                    color = MaterialTheme.colorScheme.error
//                                )
//                            }
//                        }
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
                    })
            }
        }
    }
}