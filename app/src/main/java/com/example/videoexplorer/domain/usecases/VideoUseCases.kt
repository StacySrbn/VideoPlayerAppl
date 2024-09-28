package com.example.videoexplorer.domain.usecases

data class VideoUseCases(
    val getVideos: GetVideos,
    val getVideoById: GetVideoById,
    val cacheVideos: CacheVideos
)
