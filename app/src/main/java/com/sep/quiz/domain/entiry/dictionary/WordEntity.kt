package com.sep.quiz.domain.entiry.dictionary

import kotlinx.serialization.SerialName

data class WordEntity(
    val definition: String,
    val word: String,
    val valid: Boolean,
)
