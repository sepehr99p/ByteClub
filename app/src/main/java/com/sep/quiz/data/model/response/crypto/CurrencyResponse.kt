package com.sep.quiz.data.model.response.crypto

import com.sep.quiz.domain.entiry.crypto.CurrencyEntity
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResponse(
    val currency: String,
    val name: String,
    val fullName: String,
    val precision: Int
) {
    fun toDomainModel(): CurrencyEntity = CurrencyEntity(
        currency = currency,
        name = name,
        fullName = fullName,
        precision = precision
    )
}
