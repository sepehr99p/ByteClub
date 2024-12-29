package com.sep.byteClub.domain.repository.ninja

import com.sep.byteClub.data.model.UploadImageProgress
import com.sep.byteClub.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface ImageToTextRepository {
    fun uploadImage(
        data: ByteArray,
        imageMimType: String
    ): Flow<ResultState<UploadImageProgress>>
}