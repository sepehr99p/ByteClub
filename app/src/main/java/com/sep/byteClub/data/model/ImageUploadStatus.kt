package com.sep.byteClub.data.model

sealed class ImageUploadStatus {
    data object InProgress : ImageUploadStatus()
    data object Complete : ImageUploadStatus()
}
