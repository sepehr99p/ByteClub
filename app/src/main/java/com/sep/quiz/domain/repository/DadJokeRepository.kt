package com.sep.quiz.domain.repository

import com.sep.quiz.domain.entiry.dadJoke.DadJokeEntity
import com.sep.quiz.utils.ResultState

interface DadJokeRepository {

    suspend fun getJoke(): ResultState<DadJokeEntity>

}