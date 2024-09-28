package com.example.videoexplorer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val duration: Int,
    val urlThumbnail: String
)
