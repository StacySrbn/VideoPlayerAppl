package com.example.videoexplorer

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.videoexplorer.presentation.home_screen.HomeViewModel
import com.example.videoexplorer.ui.theme.VideoExplorerTheme
import com.example.videoexplorer.util.navgraph.NavGraph
import com.example.videoexplorer.util.navgraph.Route
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(100)
            splashScreen.setKeepOnScreenCondition { false }
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            VideoExplorerTheme {
                val systemController = rememberSystemUiController()
                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = false
                    )
                }

                Box(modifier = Modifier.background(color = colorResource(id = R.color.dark_blue))){
                    val viewModel: HomeViewModel = viewModel()
                    val videoListState by viewModel.videoListState.collectAsState()
                    val startDestination = Route.HomeScreen.route

                    NavGraph(startDestination = startDestination, videos = videoListState.videoList)
                }
            }
        }
    }
}
