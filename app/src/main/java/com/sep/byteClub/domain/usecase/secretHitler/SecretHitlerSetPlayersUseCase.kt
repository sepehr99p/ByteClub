package com.sep.byteClub.domain.usecase.secretHitler

import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerRole
import com.sep.byteClub.domain.repository.secretHitler.SecretHitlerRepository
import javax.inject.Inject

class SecretHitlerSetPlayersUseCase @Inject constructor(
    private val secretHitlerRepository: SecretHitlerRepository
) {

    suspend operator fun invoke(list: List<String>) {
        val unKnownPlayers = arrayListOf<String>()
        unKnownPlayers.addAll(list)
        val finalPlayers = arrayListOf<SecretHitlerPlayerEntity>()
        var player = unKnownPlayers.random()
        finalPlayers.add(SecretHitlerPlayerEntity(name = player, role = SecretHitlerRole.HITLER))
        for (i in 0 until getLiberalCount(list)) {
            player = unKnownPlayers.random()
            finalPlayers.add(
                SecretHitlerPlayerEntity(name = player, role = SecretHitlerRole.LIBERAL)
            )
            unKnownPlayers.remove(player)
        }
        for (i in 0..<list.size - getLiberalCount(list)) {
            player = unKnownPlayers.random()
            finalPlayers.add(
                SecretHitlerPlayerEntity(name = player, role = SecretHitlerRole.FASCISM)
            )
            unKnownPlayers.remove(player)
        }
        secretHitlerRepository.setPlayers(list = finalPlayers.shuffled())
    }


    private fun getLiberalCount(list: List<String>): Int {
        return when (list.size) {
            5, 6 -> 4
            7, 8 -> 5
            9, 10 -> 6
            else -> 10
        }
    }


}