package com.sep.byteClub.domain.usecase.secretHitler

import com.sep.byteClub.domain.entity.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.domain.entity.secretHitler.SecretHitlerRole
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
        unKnownPlayers.remove(player)
        repeat(getFascismCount(list)) {
            player = unKnownPlayers.random()
            finalPlayers.add(
                SecretHitlerPlayerEntity(name = player, role = SecretHitlerRole.FASCISM)
            )
            unKnownPlayers.remove(player)
        }
        repeat(list.size - (getFascismCount(list) + 1)) {
            player = unKnownPlayers.random()
            finalPlayers.add(
                SecretHitlerPlayerEntity(name = player, role = SecretHitlerRole.LIBERAL)
            )
            unKnownPlayers.remove(player)
        }
        secretHitlerRepository.setPlayers(list = finalPlayers.shuffled())
    }


    private fun getFascismCount(list: List<String>): Int {
        return when (list.size) {
            5, 6 -> 1
            7, 8 -> 2
            9, 10 -> 3
            else -> 10
        }
    }


}