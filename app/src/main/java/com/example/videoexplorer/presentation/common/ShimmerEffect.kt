package com.example.videoexplorer.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.videoexplorer.presentation.Dimens
import com.example.videoexplorer.presentation.Dimens.ExtraSmallPadding
import com.example.videoexplorer.presentation.Dimens.RoundedCorner2
import com.example.videoexplorer.presentation.Dimens.SmallSpace
import com.example.videoexplorer.ui.theme.VideoExplorerTheme

fun Modifier.shimmerEffect(): Modifier = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value
    this.background(
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = alpha)
    )
}

@Composable
fun VideoShimmerEffectList(
){
   Column(
       modifier = Modifier
           .fillMaxWidth()
           .wrapContentHeight()
           .padding(ExtraSmallPadding)
           .clip(RoundedCornerShape(Dimens.RoundedCorner1))
   ){
       Box(
           modifier = Modifier
               .fillMaxWidth()
               .padding(ExtraSmallPadding)
               .height(Dimens.ThumbHeight)
               .clip(RoundedCornerShape(RoundedCorner2))
               .shimmerEffect()
       )
       Spacer(modifier = Modifier.height(ExtraSmallPadding))
       Box(
           modifier = Modifier
               .padding(start = SmallSpace, end = SmallSpace, bottom = SmallSpace)
               .height(15.dp)
               .fillMaxWidth()
               .clip(RoundedCornerShape(RoundedCorner2))
               .shimmerEffect()
       )
   }
}

@Composable
fun VideoShimmerEffect(
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(ExtraSmallPadding)
            .clip(RoundedCornerShape(Dimens.RoundedCorner1))
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ExtraSmallPadding)
                .height(Dimens.ThumbHeight)
                .clip(RoundedCornerShape(RoundedCorner2))
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(ExtraSmallPadding))
        Box(
            modifier = Modifier
                .padding(start = SmallSpace, end = SmallSpace, bottom = SmallSpace)
                .height(15.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(RoundedCorner2))
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(ExtraSmallPadding))
        Box(
            modifier = Modifier
                .padding(start = SmallSpace, end = SmallSpace, bottom = SmallSpace)
                .height(15.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(RoundedCorner2))
                .shimmerEffect()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleCardSimmerEffectPreview(){
    VideoExplorerTheme {
        VideoShimmerEffectList()
    }
}