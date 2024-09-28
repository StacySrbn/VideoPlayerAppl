package com.example.videoexplorer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.videoexplorer.data.local.VideoDatabase
import com.example.videoexplorer.data.remote.VideoApi
import com.example.videoexplorer.data.remote.VideoPagingSource
import com.example.videoexplorer.domain.model.MyVideo
import com.example.videoexplorer.domain.repository.VideoRepository
import com.example.videoexplorer.mapper.VideoMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoApi: VideoApi,
    private val videoMapper: VideoMapper,
    private val videoDatabase: VideoDatabase
): VideoRepository {

    override suspend fun getVideos(): Flow<PagingData<MyVideo>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {VideoPagingSource(videoApi = videoApi, videoMapper = videoMapper)}
        ).flow
    }

    override suspend fun getVideoById(id: Int): MyVideo? {

        val localVideo = videoDatabase.videoDao.getVideoById(id)
        if (localVideo!=null){
            return videoMapper.mapToMyVideo(localVideo)
        }


        val videoResponse = videoApi.getVideos()
        val video = videoResponse.videos.find { it.id == id }
        return video?.let { videoMapper.mapToMyVideo(it) }
    }

    override suspend fun cacheVideos(videos: List<MyVideo>) {
        val videoEntities = videos.map { videoMapper.mapToVideoEntity(it) }
        videoDatabase.videoDao.insertVideos(videoEntities)
    }

}