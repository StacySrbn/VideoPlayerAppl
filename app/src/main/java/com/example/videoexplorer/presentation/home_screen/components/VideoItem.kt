package com.example.videoexplorer.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.example.videoexplorer.presentation.Dimens.ExtraSmallPadding
import com.example.videoexplorer.presentation.Dimens.RoundedCorner1
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.videoexplorer.domain.model.MyVideo
import com.example.videoexplorer.presentation.Dimens.RoundedCorner2
import com.example.videoexplorer.presentation.Dimens.SmallSpace
import com.example.videoexplorer.presentation.Dimens.ThumbHeight
import getAverageColor

@Composable
fun VideoItem(
    video: MyVideo,
    onClick:() -> Unit
){

    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(video.urlThumbnail)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(ExtraSmallPadding)
            .clip(RoundedCornerShape(RoundedCorner1))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondaryContainer,
                        dominantColor
                    )
                )
            )
            .clickable {
                onClick()
            }
    ) {
        if (imageState is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(ExtraSmallPadding)
                    .height(ThumbHeight)
                    .clip(RoundedCornerShape(RoundedCorner2))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(70.dp),
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = video.title
                )
            }

        }

        if (imageState is AsyncImagePainter.State.Success) {
            dominantColor = getAverageColor(
                imageBitmap = imageState.result.drawable.toBitmap().asImageBitmap()
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ThumbHeight)
                    .padding(ExtraSmallPadding)
                    .clip(RoundedCornerShape(RoundedCorner2)),
                painter = imageState.painter,
                contentDescription = video.title,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(ExtraSmallPadding))

        Text(
            modifier = Modifier.padding(start = SmallSpace, end = SmallSpace, bottom = SmallSpace),
            text = video.title,
            fontSize = 15.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )
    }
}