package com.example.videoexplorer.util.navgraph


sealed class Route(
    val route: String
){
    object HomeScreen: Route(route = "homeScreen")
    object DetailScreen : Route(route = "detailScreen")

}
