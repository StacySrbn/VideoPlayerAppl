package com.example.videoexplorer.mapper

import com.example.videoexplorer.Constants.NO_VIDEO_URL
import com.example.videoexplorer.data.dto.Video
import com.example.videoexplorer.data.dto.VideoResponse
import com.example.videoexplorer.data.local.VideoEntity
import com.example.videoexplorer.domain.model.MyVideo

class VideoMapper {
    fun map(videoResponse: VideoResponse): List<MyVideo> {
        return videoResponse.videos.map { item ->
            MyVideo(
                id = item.id,
                duration = item.duration,
                title = item.user.name,
                urlThumbnail = item.video_pictures.firstOrNull()?.picture?: "",
                urlVideo = item.video_files.firstOrNull()?.link ?: ""

            )
        }
    }

    fun mapToMyVideo(video: Video): MyVideo { // to map a single Video
        return MyVideo(
            id = video.id,
            duration = video.duration,
            title = video.user.name,
            urlThumbnail = video.video_pictures.firstOrNull()?.picture ?: "",
            urlVideo = video.video_files.firstOrNull()?.link ?: ""
        )
    }

    fun mapToVideoEntity(domainModel: MyVideo): VideoEntity {
        return VideoEntity(
            id = domainModel.id,
            title = domainModel.title,
            duration = domainModel.duration,
            urlThumbnail = domainModel.urlThumbnail
        )
    }

    fun mapToMyVideo(videoEntity: VideoEntity): MyVideo {
        return MyVideo(
            id = videoEntity.id,
            duration = videoEntity.duration,
            title = videoEntity.title,
            urlThumbnail = videoEntity.urlThumbnail,
            urlVideo = NO_VIDEO_URL
        )
    }
}