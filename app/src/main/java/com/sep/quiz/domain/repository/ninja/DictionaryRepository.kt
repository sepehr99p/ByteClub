package com.sep.quiz.domain.repository.ninja

import com.sep.quiz.domain.entiry.dictionary.WordEntity
import com.sep.quiz.utils.ResultState

interface DictionaryRepository {

    suspend fun fetchWord(word : String) : ResultState<WordEntity>

}