package com.sep.byteClub.domain.entiry.secretHitler

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

@Serializable
data class SecretHitlerPlayerEntity(
    val name : String,
    val role : SecretHitlerRole
)


enum class SecretHitlerRole(val color: Color) {
    FASCISM(Color.Red),
    LIBERAL(Color.Blue),
    HITLER(Color.Red)
}
