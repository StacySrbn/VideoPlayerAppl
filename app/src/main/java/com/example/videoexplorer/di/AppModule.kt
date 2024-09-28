package com.example.videoexplorer.di

import android.app.Application
import androidx.room.Room
import com.example.videoexplorer.Constants.BASE_URL
import com.example.videoexplorer.data.local.VideoDao
import com.example.videoexplorer.data.local.VideoDatabase
import com.example.videoexplorer.data.remote.VideoApi
import com.example.videoexplorer.data.repository.VideoRepositoryImpl
import com.example.videoexplorer.domain.repository.VideoRepository
import com.example.videoexplorer.domain.usecases.CacheVideos
import com.example.videoexplorer.domain.usecases.GetVideoById
import com.example.videoexplorer.domain.usecases.GetVideos
import com.example.videoexplorer.domain.usecases.VideoUseCases
import com.example.videoexplorer.mapper.VideoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesVideoApi(): VideoApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VideoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideVideoDatabase(
        application: Application
    ) : VideoDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = VideoDatabase::class.java,
            name = "video_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideVideoDao(
        videoDatabase: VideoDatabase
    ) : VideoDao = videoDatabase.videoDao

    @Provides
    @Singleton
    fun providesVideoMapper(): VideoMapper {
        return VideoMapper()
    }

    @Provides
    @Singleton
    fun providesVideoRepository(
        videoApi: VideoApi,
        videoMapper: VideoMapper,
        videoDatabase: VideoDatabase
    ) : VideoRepository = VideoRepositoryImpl(videoApi, videoMapper, videoDatabase)



    @Provides
    @Singleton
    fun providesVideosUseCases(
        videoRepository: VideoRepository
    ) : VideoUseCases {
        return VideoUseCases(
            getVideos = GetVideos(videoRepository),
            cacheVideos = CacheVideos(videoRepository),
            getVideoById = GetVideoById(videoRepository)
        )
    }


}