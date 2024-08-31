package com.sep.byteClub.domain.usecase.dictionary

import com.sep.byteClub.domain.repository.ninja.DictionaryRepository
import javax.inject.Inject

class GetWordUseCase @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) {

    suspend operator fun invoke(word: String) = dictionaryRepository.fetchWord(word = word)

}