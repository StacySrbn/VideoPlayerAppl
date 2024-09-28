Video Explorer Application by Anastasiia Serbina 

Project Overview
The Video Explorer application is an Android app designed to fetch, display, and play videos from a remote API. 

Key Features
Home Screen: Displays a list of videos fetched from a remote server, including a loading effect during data retrieval.
Video Details Screen: Allows users to view detailed information about a selected video and play it using ExoPlayer.
Pagination: Implements infinite scrolling to load more videos seamlessly as the user scrolls through the list.
Caching: Utilizes Room database for local caching of video information to enhance performance and offline access.

Technologies Used
Programming Language: Kotlin
Libraries:
Retrofit: For network calls and API integration.
ExoPlayer: For video playback functionality.
Room: For local data persistence.
Coroutines: For asynchronous programming and background tasks.
Paging 3: For efficient loading and displaying of large datasets.
Dagger Hilt: For dependency injection, streamlining the management of dependencies throughout the app.
