package com.sep.byteClub.data.model.response.f1

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriversDto(
    @SerialName("broadcast_name") val broadcastName : String,
    @SerialName("driver_number") val number : Long,
    @SerialName("full_name") val fullName : String,
    @SerialName("name_acronym") val acronym : String,
    @SerialName("headshot_url") val facePictureUrl : String,
    @SerialName("team_colour") val teamColor : String,
    @SerialName("team_name") val teamName : String,
)