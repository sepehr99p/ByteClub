package com.sep.byteClub.data.repository.ninja

import com.sep.byteClub.data.model.ImageUploadStatus
import com.sep.byteClub.data.model.ProgressRequestBody
import com.sep.byteClub.data.model.UploadImageProgress
import com.sep.byteClub.data.model.response.imageToText.ImageToTextResponse
import com.sep.byteClub.data.remote.ninja.ImageToTextApiService
import com.sep.byteClub.domain.repository.ninja.ImageToTextRepository
import com.sep.byteClub.utils.ResultState
import com.sep.byteClub.utils.callAdapter.NetworkResponse
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.MultipartBody


class ImageToTextRepositoryImpl @Inject constructor(
    val imageToTextApiService: ImageToTextApiService
) : ImageToTextRepository {
    override fun uploadImage(
        data: ByteArray,
        imageMimType: String
    ): Flow<ResultState<UploadImageProgress>> {
        return uploadImageInternal(data, imageMimType) { multiPart ->
            imageToTextApiService.getTextFromImage(multiPart)
        }
    }

    private fun uploadImageInternal(
        data: ByteArray,
        imageMimType: String,
        uploadFunction: suspend (MultipartBody.Part) -> NetworkResponse<ImageToTextResponse>
    ): Flow<ResultState<UploadImageProgress>> = callbackFlow {
        try {
            send(
                ResultState.Success(
                    UploadImageProgress(
                        status = ImageUploadStatus.InProgress,
                        0f
                    )
                )
            )

            val requestBody = ProgressRequestBody(data, imageMimType) { progress ->
                trySend(
                    ResultState.Success(
                        UploadImageProgress(
                            ImageUploadStatus.InProgress,
                            progress / 100f
                        )
                    )
                )
            }

            val multipartBody =
                MultipartBody.Part.createFormData("file", "tempFile", requestBody)
            when (val result = uploadFunction(multipartBody)) {
                is NetworkResponse.ApiError -> {
                    send(ResultState.Failure(error = "error"))
                }

                is NetworkResponse.Exception -> {
                    send(ResultState.Exception(result.throwable))
                }

                is NetworkResponse.Success -> {
                    send(ResultState.Success(UploadImageProgress(ImageUploadStatus.Complete, 100f)))
                    close()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            trySend(ResultState.Exception(e))
        } finally {
            close()
        }
    }

}

