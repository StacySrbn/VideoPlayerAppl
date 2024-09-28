package com.example.videoexplorer.data.remote

import com.example.videoexplorer.Constants.API_KEY
import com.example.videoexplorer.data.dto.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface VideoApi {
    @GET("videos/popular")
    suspend fun getVideos(
        @Header("Authorization") apiKey: String = API_KEY,
        @Query("query") query: String = "nature",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 15
    ): VideoResponse
}