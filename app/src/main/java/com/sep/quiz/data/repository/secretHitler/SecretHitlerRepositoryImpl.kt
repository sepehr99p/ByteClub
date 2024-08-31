package com.sep.quiz.data.repository.secretHitler

import com.sep.quiz.SecretHitlerPlayer
import com.sep.quiz.data.local.datastore.secretHitler.SecretHitlerDataSource
import com.sep.quiz.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.quiz.domain.entiry.secretHitler.SecretHitlerRole
import com.sep.quiz.domain.repository.secretHitler.SecretHitlerRepository
import com.sep.quiz.secretHitlerPlayer
import com.sep.quiz.secretHitlerPlayerListPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SecretHitlerRepositoryImpl @Inject constructor(
    private val dataSource: SecretHitlerDataSource
) : SecretHitlerRepository {

    private fun SecretHitlerPlayerEntity.toDataSource(): SecretHitlerPlayer = secretHitlerPlayer {
        name = this@toDataSource.name
        role = this@toDataSource.role.name
    }

    fun SecretHitlerPlayer.toDomainModel(): SecretHitlerPlayerEntity = SecretHitlerPlayerEntity(
        name = this.name,
        role = SecretHitlerRole.valueOf(this.role)
    )

    override suspend fun setPlayers(list: List<SecretHitlerPlayerEntity>) {
        dataSource.addPlayersList(secretHitlerPlayerListPreferences {
            myPlayers.addAll(list.map { it.toDataSource() })
        })
    }

    override suspend fun fetchLastGamePlayers(): Flow<List<SecretHitlerPlayerEntity>> {
        return dataSource.getPlayers()
            .map { preferences -> preferences.myPlayersList.map { it.toDomainModel() } }
    }
}