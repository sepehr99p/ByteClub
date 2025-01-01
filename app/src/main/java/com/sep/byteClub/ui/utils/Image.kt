package com.sep.byteClub.ui.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build.VERSION.SDK_INT
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.isNotEmpty
import com.sep.byteClub.R
import com.sep.byteClub.ui.designSystem.ex.shimmerLoadingAnimation
import com.sep.byteClub.ui.designSystem.theme.dimen.corner_16


@Composable
fun ColorScheme.isLight() = this.background.luminance() > 0.5


@Composable
fun ThemeAsyncImage(
    modifier: Modifier = Modifier,
    model: String?,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    placeHolderRes: Int = R.drawable.logo
) {
    val optimizedUrl = getOptimizedUrl(model)

    DynamicAsyncImage(
        modifier = modifier,
        imageUrl = optimizedUrl,
        contentDescription = contentDescription,
        contentScale = contentScale,
        placeHolderRes = placeHolderRes)

}

@Composable
internal fun getOptimizedUrl(model: String?) = if (model?.isNotEmpty() == true) {
    if (MaterialTheme.colorScheme.isLight()) {
        model.replace("/dark/", "/light/")
    } else {
        model.replace("/light/", "/dark/")
    }
} else {
    ""
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ThemeLocalImage(
    modifier: Modifier = Modifier,
    model: Int,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    placeHolderRes: Int = R.drawable.logo,
    colorFilter: ColorFilter? = null
) {
    GlideImage(
        modifier = modifier,
        model = model,
        contentDescription = contentDescription,
        contentScale = contentScale,
        failure = placeholder(placeHolderRes),
        colorFilter = colorFilter
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideBase64Image(
    modifier: Modifier = Modifier,
    base64String: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    placeHolderRes: Int = R.drawable.logo,
    colorFilter: ColorFilter? = null
) {
    val coroutineScope = rememberCoroutineScope()
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    if (base64String.isNotEmpty())
        LaunchedEffect(Unit) {
            coroutineScope.launch(Dispatchers.Default){
                try {
                    val byteArray = Base64.decode(base64String, Base64.DEFAULT)
                    if (byteArray!=null && byteArray.isNotEmpty())  bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap().asAndroidBitmap()
                }catch (e: Exception){e.printStackTrace()}
            }
        }
    GlideBitmapImage(modifier, bitmap, contentDescription, contentScale, placeHolderRes, colorFilter)
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun GlideBitmapImage(
    modifier: Modifier,
    bitmap: Bitmap?,
    contentDescription: String?,
    contentScale: ContentScale,
    placeHolderRes: Int,
    colorFilter: ColorFilter?
) {
    GlideImage(
        modifier = modifier,
        model = bitmap,
        contentDescription = contentDescription,
        contentScale = contentScale,
        failure = placeholder(placeHolderRes),
        colorFilter = colorFilter
    )
}


@Composable
private fun DynamicAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeHolderRes: Int,
    contentScale: ContentScale,
) {
    val iconTint = Color.DarkGray
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    val decoderImageLoader =  ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .respectCacheHeaders(false)
        .build()

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .allowHardware(false)
        .networkCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()




    val imageLoader = rememberAsyncImagePainter(
        model = imageRequest,
        imageLoader = decoderImageLoader,
        onState = { state ->
            isLoading = state is AsyncImagePainter.State.Loading
            isError = state is AsyncImagePainter.State.Error
        },
    )
    val isLocalInspection = LocalInspectionMode.current



    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading && !isLocalInspection) {
            ImageLoadingShimmerEffect(modifier = modifier)
        }
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = contentScale,
            painter = if (isError.not() && !isLocalInspection) imageLoader else painterResource(id = placeHolderRes),
            contentDescription = contentDescription,
            colorFilter = if (iconTint != Color.Unspecified) iconTint?.let { ColorFilter.tint(it) } else null,
        )
    }

}

@Composable
private fun ImageLoadingShimmerEffect(modifier: Modifier = Modifier) {
    var positionInRootTopBar by remember { mutableStateOf(Offset.Zero) }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(corner_16))
            .background(Color.DarkGray)
            .shimmerLoadingAnimation()
            .fillMaxSize()
            .onGloballyPositioned {
                positionInRootTopBar = it.positionInRoot()
            }
    )
}


@Composable
fun asyncImageLoader(url: String): Painter? {

    val imageUrl = getOptimizedUrl(model = url)
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .allowHardware(false)
        .networkCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    val decoderImageLoader =  ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val imageLoader = rememberAsyncImagePainter(
        model = imageRequest,
        imageLoader = decoderImageLoader,
        onState = { state ->
            isLoading = state is AsyncImagePainter.State.Loading
            isError = state is AsyncImagePainter.State.Error
        },
    )
    val isLocalInspection = LocalInspectionMode.current

    return if (isError.not() && !isLocalInspection) imageLoader else null
}
