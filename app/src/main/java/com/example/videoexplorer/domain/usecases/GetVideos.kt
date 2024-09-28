package com.example.videoexplorer.domain.usecases

import androidx.paging.PagingData
import com.example.videoexplorer.domain.model.MyVideo
import com.example.videoexplorer.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetVideos @Inject constructor(
    private val videoRepository: VideoRepository
) {

    suspend operator fun invoke(): Flow<PagingData<MyVideo>> {
        return videoRepository.getVideos()
    }
}