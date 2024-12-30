package com.sep.byteClub.data.model.response.f1

import com.sep.byteClub.domain.entity.f1.RaceMeetingEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeetingDto(
    @SerialName("circuit_key") val circuitKey: Long,
    @SerialName("meeting_key") val meetingKey: Long,
    @SerialName("circuit_short_name") val shortName: String,
    @SerialName("meeting_name") val name: String,
    @SerialName("date_start") val dateStart: String,
    @SerialName("gmt_offset") val gmtOffset: String,
) {
    internal fun toDomainModel(): RaceMeetingEntity = RaceMeetingEntity(
        circuitKey, meetingKey, shortName, name, dateStart, gmtOffset
    )
}
