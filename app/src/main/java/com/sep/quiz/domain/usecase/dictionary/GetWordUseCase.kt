package com.sep.quiz.domain.usecase.dictionary

import com.sep.quiz.domain.repository.ninja.DictionaryRepository
import javax.inject.Inject

class GetWordUseCase @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) {

    suspend operator fun invoke(word: String) = dictionaryRepository.fetchWord(word = word)

}