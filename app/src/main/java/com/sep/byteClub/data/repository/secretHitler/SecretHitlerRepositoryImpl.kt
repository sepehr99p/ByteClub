package com.sep.byteClub.data.repository.secretHitler

import com.sep.byteClub.SecretHitlerPlayer
import com.sep.byteClub.data.local.datastore.secretHitler.SecretHitlerDataSource
import com.sep.byteClub.domain.entity.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.domain.entity.secretHitler.SecretHitlerRole
import com.sep.byteClub.domain.repository.secretHitler.SecretHitlerRepository
import com.sep.byteClub.secretHitlerPlayer
import com.sep.byteClub.secretHitlerPlayerListPreferences
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