package com.geek.android4_5_youtube.data.remote

import com.geek.android4_5_youtube.model.Playlist
import com.geek.android4_5_youtube.model.PlaylistDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/youtube/v3/playlists")
    suspend fun fetchAllPlaylist(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String
    ): Response<Playlist>

    @GET("/youtube/v3/playlistItems")
    suspend fun fetchPlaylist(
        @Query("part") part: String,
        @Query("maxResults") max: String,
        @Query("playlistId") playlistId:String,
        @Query("key") apiKey: String
    ):Response<PlaylistDetail>
}