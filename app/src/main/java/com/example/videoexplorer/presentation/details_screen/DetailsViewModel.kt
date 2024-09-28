package com.example.videoexplorer.presentation.details_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoexplorer.domain.repository.VideoRepository
import com.example.videoexplorer.domain.usecases.VideoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val videoUseCases: VideoUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = MutableStateFlow(VideoDetailsState())
    val state: StateFlow<VideoDetailsState> = _state

    init {
        val videoId: Int? = savedStateHandle["videoId"]
        videoId?.let {
            getVideoDetails(it)
        }
    }

    private fun getVideoDetails(id: Int) {
        viewModelScope.launch {
            _state.value = VideoDetailsState(isLoading = true)
            val video = videoUseCases.getVideoById(id)
            _state.value = VideoDetailsState(isLoading = false, video = video)
        }
    }
}