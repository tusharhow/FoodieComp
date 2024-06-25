package com.example.foodiecomp.src.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun SvgImageFromDrawable(assetName: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    val request = ImageRequest.Builder(context)
        .data("file:///android_asset/$assetName")
        .size(Size.ORIGINAL)
        .build()

    val painter: Painter = rememberAsyncImagePainter(model = request, imageLoader = imageLoader)

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
    )
}
