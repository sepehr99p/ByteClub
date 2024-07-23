package com.sep.quiz.data.repository.ninja

import com.sep.quiz.data.remote.ninja.DadJokesApiService
import com.sep.quiz.domain.entiry.dadJoke.DadJokeEntity
import com.sep.quiz.domain.repository.ninja.DadJokeRepository
import com.sep.quiz.utils.ResultState
import javax.inject.Inject

class DadJokeRepositoryImpl @Inject constructor(
    private val dadJokesApiService: DadJokesApiService
) : DadJokeRepository {
    override suspend fun getJoke(): ResultState<DadJokeEntity> {
        val result = dadJokesApiService.getJoke()
        if (result.isSuccessful) {
            result.body()?.let {
                return ResultState.Success(it.first().toDomainModel())
            }
        }
        return ResultState.Failure(result.message())
    }
}