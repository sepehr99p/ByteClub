package com.sep.quiz.domain.entiry.secretHitler

import kotlinx.serialization.Serializable

@Serializable
data class SecretHitlerPlayerEntity(
    val name : String,
    val role : SecretHitlerRole
)


enum class SecretHitlerRole {
    FASCISM, LIBERAL, HITLER
}
