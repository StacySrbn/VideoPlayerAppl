package com.example.videoexplorer.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.videoexplorer.domain.model.MyVideo
import com.example.videoexplorer.mapper.VideoMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class VideoPagingSource(
    private val videoApi: VideoApi,
    private val videoMapper: VideoMapper
) : PagingSource<Int, MyVideo>() {

    override fun getRefreshKey(state: PagingState<Int, MyVideo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyVideo> {
        return try {

            val videosResponse = withContext(Dispatchers.IO) {
                videoApi.getVideos(perPage = params.loadSize)
            }


            val videos = videoMapper.map(videosResponse)


            val totalResults = videosResponse.total_results
            val loadedResults = videos.size

            LoadResult.Page(
                data = videos,

                nextKey = if (loadedResults >= totalResults || videos.size < params.loadSize) null else (params.key ?: 1) + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            return when (e) {

                is IOException -> {

                    LoadResult.Error(e)
                }
                is HttpException -> {

                    LoadResult.Error(e)
                }
                else -> {

                    LoadResult.Error(e)
                }
            }
        }
    }
}
