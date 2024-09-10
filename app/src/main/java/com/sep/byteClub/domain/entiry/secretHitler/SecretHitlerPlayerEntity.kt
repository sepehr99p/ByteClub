package com.sep.byteClub.domain.entiry.secretHitler

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

@Serializable
data class SecretHitlerPlayerEntity(
    val name : String,
    val role : SecretHitlerRole
)


enum class SecretHitlerRole(val color: Color) {
    FASCISM(Color(0xFF630101)),
    LIBERAL(Color(0xFF3BB7CC)),
    HITLER(Color(0xFF630101))
}
