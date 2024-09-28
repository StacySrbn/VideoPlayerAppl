package com.example.videoexplorer.util.navgraph

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.videoexplorer.domain.model.MyVideo
import com.example.videoexplorer.presentation.details_screen.DetailScreen
import com.example.videoexplorer.presentation.home_screen.HomeScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NavGraph(
    startDestination: String,
    videos: Flow<PagingData<MyVideo>> = emptyFlow()
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Route.HomeScreen.route) {
            HomeScreen(videos = videos) { videoId, videoIndex ->
                navController.navigate("${Route.DetailScreen.route}/$videoId?index=$videoIndex")
            }
        }
        composable(
            route = "${Route.DetailScreen.route}/{videoId}?index={index}",
            arguments = listOf(
                navArgument("videoId") { type = NavType.IntType },
                navArgument("index") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val currentIndex = backStackEntry.arguments?.getInt("index")
            if (currentIndex != null) {
                val lazyPagingItems = videos.collectAsLazyPagingItems()
                val videosList = lazyPagingItems.itemSnapshotList.items
                DetailScreen(navController = navController, videos = videosList, currentIndex = currentIndex)
            }
        }
    }
}