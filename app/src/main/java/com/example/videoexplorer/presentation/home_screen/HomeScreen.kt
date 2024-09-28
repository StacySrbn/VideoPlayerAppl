package com.example.videoexplorer.presentation.home_screen

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.videoexplorer.domain.model.MyVideo
import com.example.videoexplorer.presentation.home_screen.components.TopBar
import com.example.videoexplorer.presentation.home_screen.components.VideoList
import kotlinx.coroutines.flow.Flow

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeScreen(
    videos: Flow<PagingData<MyVideo>>,
    navigate: (Int, Int) -> Unit
){

    val lazyPagingItems = videos.collectAsLazyPagingItems()

    Column {
        TopBar()
        VideoList(
            videos = lazyPagingItems,
            onClick = { video, index ->
                navigate(video.id, index)
            }
        )
    }


}


