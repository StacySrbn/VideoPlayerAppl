package com.example.videoexplorer.presentation.details_screen

import com.example.videoexplorer.domain.model.MyVideo

data class VideoDetailsState(
    val isLoading: Boolean = false,
    val video: MyVideo? = null
)

