package com.sep.byteClub.domain.usecase.joke

import com.sep.byteClub.domain.repository.ninja.DadJokeRepository
import javax.inject.Inject

class DadJokeUseCase @Inject constructor(
    private val dadJokeRepository: DadJokeRepository
) {

    suspend operator fun invoke() = dadJokeRepository.getJoke()

}