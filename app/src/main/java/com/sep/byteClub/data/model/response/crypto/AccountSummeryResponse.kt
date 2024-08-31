package com.sep.byteClub.data.model.response.crypto

import kotlinx.serialization.Serializable

@Serializable
data class AccountSummeryResponse(
    val level: Int,//User level
    val subQuantity: Int, //Number of sub-accounts
    val maxDefaultSubQuantity: Int, // Max number of default open sub-accounts (according to level)
    val maxSubQuantity: Int, //Max number of sub-accounts = maxDefaultSubQuantity + maxSpotSubQuantity
    val spotSubQuantity: Int, //Number of sub-accounts with spot trading permissions enabled
    val marginSubQuantity: Int, // Number of sub-accounts with margin trading permissions enabled
    val futuresSubQuantity: Int, //Number of sub-accounts with futures trading permissions enabled
    val maxSpotSubQuantity: Int, //Max number of sub-accounts with additional Spot trading permissions
    val maxMarginSubQuantity: Int, //Max number of sub-accounts with additional margin trading permissions
    val maxFuturesSubQuantity: Int // Max number of sub-accounts with additional futures trading permissions
)
