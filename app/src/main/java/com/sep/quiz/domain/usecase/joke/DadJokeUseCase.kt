package com.sep.quiz.domain.usecase.joke

import com.sep.quiz.domain.repository.ninja.DadJokeRepository
import javax.inject.Inject

class DadJokeUseCase @Inject constructor(
    private val dadJokeRepository: DadJokeRepository
) {

    suspend operator fun invoke() = dadJokeRepository.getJoke()

}