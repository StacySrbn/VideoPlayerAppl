package com.example.videoexplorer.presentation.home_screen

import androidx.paging.PagingData
import com.example.videoexplorer.domain.model.MyVideo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class VideoListState(
    val isLoading: Boolean = false,
    val videoList: Flow<PagingData<MyVideo>> = emptyFlow()
)
