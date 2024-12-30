package com.sep.byteClub.data.model.response.dadJokes

import com.sep.byteClub.domain.entity.dadJoke.DadJokeEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DadJokeResponse(
    val jokes: List<DadJokeDto>
)

@Serializable
data class DadJokeDto(
    @SerialName("joke") val joke: String
) {
    fun toDomainModel(): DadJokeEntity = DadJokeEntity(
        joke = joke
    )
}
