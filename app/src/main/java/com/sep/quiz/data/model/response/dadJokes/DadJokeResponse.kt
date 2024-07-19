package com.sep.quiz.data.model.response.dadJokes

import com.sep.quiz.domain.entiry.dadJoke.DadJokeEntity
import com.sep.quiz.utils.callAdapter.BaseNetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DadJokeResponse(
    @SerialName("joke") val joke: String
) : BaseNetworkResponse() {
    fun toDomainModel(): DadJokeEntity = DadJokeEntity(
        joke = joke
    )
}
