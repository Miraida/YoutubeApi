package com.geek.android4_5_youtube.data.remote

import com.geek.android4_5_youtube.model.Playlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/youtube/v3/playlists")
    fun fetchAllPlaylist(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String) : Call<Playlist>

}