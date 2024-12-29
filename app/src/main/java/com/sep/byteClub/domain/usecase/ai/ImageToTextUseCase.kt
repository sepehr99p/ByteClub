package com.sep.byteClub.domain.usecase.ai

import com.sep.byteClub.domain.repository.ninja.ImageToTextRepository
import javax.inject.Inject

class ImageToTextUseCase @Inject constructor(
    private val imageToTextRepository: ImageToTextRepository
) {

    suspend operator fun invoke(data: ByteArray, imageMimType: String) =
        imageToTextRepository.uploadImage(data,imageMimType)

}