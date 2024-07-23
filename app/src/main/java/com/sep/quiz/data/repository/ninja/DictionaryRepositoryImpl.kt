package com.sep.quiz.data.repository.ninja

import com.sep.quiz.data.remote.ninja.DictionaryApiService
import com.sep.quiz.domain.entiry.dictionary.WordEntity
import com.sep.quiz.domain.repository.ninja.DictionaryRepository
import com.sep.quiz.utils.ResultState
import com.sep.quiz.utils.toResultState
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryApiService: DictionaryApiService
) : DictionaryRepository {
    override suspend fun fetchWord(word: String): ResultState<WordEntity> =
        dictionaryApiService.dictionary(word).toResultState(onSuccess = {
            ResultState.Success(it.toDomainModel())
        })
}