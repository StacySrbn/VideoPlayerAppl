package com.example.videoexplorer.presentation.details_screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.videoexplorer.domain.model.MyVideo
import com.example.videoexplorer.presentation.Dimens.MediumSpace
import com.example.videoexplorer.presentation.common.EmptyScreen
import com.example.videoexplorer.presentation.common.VideoShimmerEffect
import com.example.videoexplorer.presentation.details_screen.components.DetailsBody
import com.example.videoexplorer.presentation.details_screen.components.TopBarDetail
import com.example.videoexplorer.presentation.details_screen.components.VideoPlayer
import com.example.videoexplorer.util.navgraph.Route

@Composable
fun DetailScreen(
    navController: NavController,
    videos: List<MyVideo>,
    currentIndex: Int 
){
    val viewModel: DetailsViewModel = hiltViewModel()
    val detailsState = viewModel.state.collectAsState().value

    Column{
        TopBarDetail(navController = navController)

        detailsState.video?.let { video ->
            VideoPlayer(videoUrl = video.urlVideo)
            Spacer(modifier = Modifier.height(MediumSpace))
            DetailsBody(
                video = video,
                onPreviousClick = {
                    if (currentIndex > 0){
                        navController.navigate("${Route.DetailScreen.route}/${videos[currentIndex-1].id}?index=${currentIndex-1}")
                    }
                },
                onNextClick = {
                    if(currentIndex < videos.lastIndex){
                        navController.navigate("${Route.DetailScreen.route}/${videos[currentIndex+1].id}?index=${currentIndex+1}")
                    }
                },
                canGoPrevious = currentIndex>0,
                canGoNext = currentIndex<videos.lastIndex
            )
        } ?: run {
            if (detailsState.isLoading) {
                VideoShimmerEffect()
            } else {
                EmptyScreen()
            }
        }
    }

}