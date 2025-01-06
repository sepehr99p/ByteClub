package com.sep.byteClub.data.model.response.cars

import com.sep.byteClub.utils.callAdapter.BaseNetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CarDto(
    @SerialName("city_mpg") val city : Int? = null,
    @SerialName("class") val carClass : String? = null,
    @SerialName("combination_mpg") val combination : Int? = null,
    @SerialName("cylinders") val cylinders : Int? = null,
    @SerialName("displacement") val displacement : Double? = null,
    @SerialName("highway_mpg") val highway : Int? = null,
    @SerialName("year") val year : Int? = null,
    @SerialName("drive") val drive : String? = null,
    @SerialName("fuel_type") val fuelType : String? = null,
    @SerialName("make") val make : String? = null,
    @SerialName("model") val model : String? = null,
    @SerialName("transmission") val transmission : String? = null,
)
