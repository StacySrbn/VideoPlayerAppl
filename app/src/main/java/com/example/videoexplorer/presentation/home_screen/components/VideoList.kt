package com.example.videoexplorer.presentation.home_screen.components

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.videoexplorer.domain.model.MyVideo
import com.example.videoexplorer.presentation.Dimens.MediumSpace
import com.example.videoexplorer.presentation.Dimens.SmallSpace
import com.example.videoexplorer.presentation.common.EmptyScreen
import com.example.videoexplorer.presentation.common.VideoShimmerEffectList

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun VideoList(
    videos: LazyPagingItems<MyVideo>,
    onClick: (MyVideo, Int) -> Unit

){
    val handlePagingResult = handlePagingResult(videos = videos)
    if(handlePagingResult){
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MediumSpace),
            verticalArrangement = Arrangement.spacedBy(SmallSpace)
        ){
            items(count = videos.itemCount){ index ->
                videos[index]?.let{video->
                    VideoItem(video = video, onClick = {onClick(video, index)})
                }
            }
        }
    }

}
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun handlePagingResult(
    videos: LazyPagingItems<MyVideo>

) : Boolean {
    val loadState = videos.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffectList()
            false
        }
        error != null -> {
            EmptyScreen()
            false
        }
        else -> true
    }
}

@Composable
private fun ShimmerEffectList(){
    Column(
        verticalArrangement = Arrangement.spacedBy(SmallSpace)
    ) {
        repeat(10){
            VideoShimmerEffectList()
        }
    }
}