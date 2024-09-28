package com.example.videoexplorer.domain.repository


import androidx.paging.PagingData
import com.example.videoexplorer.domain.model.MyVideo
import kotlinx.coroutines.flow.Flow

interface VideoRepository {

    suspend fun getVideos(): Flow<PagingData<MyVideo>>

    suspend fun getVideoById(id: Int): MyVideo?

    suspend fun cacheVideos(videos: List<MyVideo>)

}