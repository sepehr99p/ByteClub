package com.sep.byteClub.data.model.response.f1

import com.sep.byteClub.domain.entity.f1.IntervalEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IntervalsDto(
    @SerialName("date") val date: String,
    @SerialName("driver_number") val driverNumber: Long,
    @SerialName("gap_to_leader") val gapToLeader: Double,
    @SerialName("interval") val interval: Double,
) {
    internal fun toDomainModel(): IntervalEntity = IntervalEntity(
        date, driverNumber, gapToLeader, interval
    )
}