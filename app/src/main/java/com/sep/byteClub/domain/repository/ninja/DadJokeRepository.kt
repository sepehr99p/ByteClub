package com.sep.byteClub.domain.repository.ninja

import com.sep.byteClub.domain.entiry.dadJoke.DadJokeEntity
import com.sep.byteClub.utils.ResultState

interface DadJokeRepository {

    suspend fun getJoke(): ResultState<DadJokeEntity>

}