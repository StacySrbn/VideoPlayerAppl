package com.example.videoexplorer.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.videoexplorer.domain.model.MyVideo
import com.example.videoexplorer.domain.usecases.VideoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val videosUseCases: VideoUseCases
) : ViewModel() {

    private var _videoListState = MutableStateFlow(VideoListState())
    val videoListState = _videoListState.asStateFlow()

    init {
        getVideoList()
    }

    private fun getVideoList() {
        viewModelScope.launch {
            _videoListState.value = _videoListState.value.copy(isLoading = true)
            val videoFlow = videosUseCases.getVideos()
                .cachedIn(viewModelScope)

            videoFlow.collectLatest { pagingData ->
                val videoList = mutableListOf<MyVideo>()
                pagingData.map { video ->
                    videoList.add(video)
                    if (videoList.size >= 10) {
                        videosUseCases.cacheVideos(videoList)
                        videoList.clear()
                    }
                }

                if (videoList.isNotEmpty()) {
                    videosUseCases.cacheVideos(videoList)
                }


                _videoListState.value = _videoListState.value.copy(
                    videoList = videoFlow,
                    isLoading = false
                )
            }
        }
    }
}