package com.sep.byteClub.data.repository.ninja

import com.sep.byteClub.data.remote.ninja.NinjaApiService
import com.sep.byteClub.domain.entity.dictionary.WordEntity
import com.sep.byteClub.domain.repository.ninja.DictionaryRepository
import com.sep.byteClub.utils.ResultState
import com.sep.byteClub.utils.toResultState
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryApiService: NinjaApiService
) : DictionaryRepository {
    override suspend fun fetchWord(word: String): ResultState<WordEntity> =
        dictionaryApiService.dictionary(word).toResultState(onSuccess = {
            ResultState.Success(it.toDomainModel())
        })
}