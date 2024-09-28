package com.example.videoexplorer.domain.usecases

import com.example.videoexplorer.domain.model.MyVideo
import com.example.videoexplorer.domain.repository.VideoRepository
import javax.inject.Inject

class CacheVideos @Inject constructor(
    private val videoRepository: VideoRepository
){
    suspend operator fun invoke(videos: List<MyVideo>) {
        videoRepository.cacheVideos(videos)
    }
}