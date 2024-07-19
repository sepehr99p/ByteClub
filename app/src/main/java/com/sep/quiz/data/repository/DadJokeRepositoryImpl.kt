package com.sep.quiz.data.repository

import com.sep.quiz.data.remote.DadJokesApiService
import com.sep.quiz.domain.entiry.dadJoke.DadJokeEntity
import com.sep.quiz.domain.repository.DadJokeRepository
import com.sep.quiz.utils.ResultState
import com.sep.quiz.utils.toResultState
import javax.inject.Inject

class DadJokeRepositoryImpl @Inject constructor(
    private val dadJokesApiService: DadJokesApiService
) : DadJokeRepository {
    override suspend fun getJoke(): ResultState<DadJokeEntity> =
        dadJokesApiService.getJoke().toResultState(onSuccess = {
            ResultState.Success(it.toDomainModel())
        })
}