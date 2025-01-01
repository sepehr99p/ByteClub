package com.sep.byteClub.ui.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.sep.byteClub.R

fun Activity.openAppSettings() = startActivity(
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null),
    )
)

enum class ImagePermissionStatus {
    GRANTED,
    REJECTED,
    NOT_GRANTED,
    INIT,
}


enum class GetPermissionType(
    val iconRes: Int,
    val titleRes: Int,
    val descriptionRes: Int
) {
    CAMERA(
        iconRes = R.drawable.ic_camera_permission,
        titleRes = R.string.camera_permission_title,
        descriptionRes = R.string.camera_permission_description
    ),
    GALLERY(
        iconRes = R.drawable.ic_gallary_permission,
        titleRes = R.string.gallery_permission_title,
        descriptionRes = R.string.gallery_permission_description
    )
}
