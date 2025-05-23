package com.sep.byteClub.domain.repository.ninja

import com.sep.byteClub.domain.entity.dictionary.WordEntity
import com.sep.byteClub.utils.ResultState

interface DictionaryRepository {

    suspend fun fetchWord(word : String) : ResultState<WordEntity>

}