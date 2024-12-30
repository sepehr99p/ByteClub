package com.sep.byteClub.data.model.response.dictionary

import com.sep.byteClub.domain.entity.dictionary.WordEntity
import com.sep.byteClub.utils.callAdapter.BaseNetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WordResponse(
    @SerialName("definition") val definition: String?,
    @SerialName("word") val word: String?,
    @SerialName("valid") val valid: Boolean?,
) : BaseNetworkResponse() {
    fun toDomainModel(): WordEntity =
        WordEntity(
            definition = definition.orEmpty(),
            word = word.orEmpty(),
            valid = valid ?: false
        )
}
