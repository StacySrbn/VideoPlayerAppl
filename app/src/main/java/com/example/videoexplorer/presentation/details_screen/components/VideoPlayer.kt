package com.example.videoexplorer.presentation.details_screen.components

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.*
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.ui.PlayerView
import com.example.videoexplorer.presentation.Dimens
import com.example.videoexplorer.presentation.Dimens.RoundedCorner2
import com.example.videoexplorer.presentation.Dimens.ThumbHeight

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(
    videoUrl: String
){
    val context = LocalContext.current

    val trackSelector = remember {
        DefaultTrackSelector(context).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }
    }

    val player = remember {
        ExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .build().apply {
            setMediaItem(MediaItem.fromUri(videoUrl))
                addListener(object : Player.Listener {
                    override fun onPlayerError(error: PlaybackException) {
                        Log.e("VideoPlayer", "Player error: ${error.message}")
                    }
                })
        }
    }

    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }


    val playerView = PlayerView(context)
    playerView.player = player

    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = playWhenReady
    }

    DisposableEffect(player) {
        onDispose {
            player.release()
        }
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(ThumbHeight)
            .padding(horizontal = Dimens.MediumSpace)
            .clip(RoundedCornerShape(RoundedCorner2)),
        factory = {playerView}
    )
}