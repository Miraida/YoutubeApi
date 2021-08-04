package com.geek.android4_5_youtube.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geek.android4_5_youtube.BuildConfig.API_KEY
import com.geek.android4_5_youtube.core.network.Resource
import com.geek.android4_5_youtube.data.remote.ApiService
import com.geek.android4_5_youtube.model.Playlist
import com.geek.android4_5_youtube.model.PlaylistDetail
import com.geek.android4_5_youtube.utils.Constant
import kotlinx.coroutines.Dispatchers

class Repository(private val apiService: ApiService) {

    fun fetchYoutubeApiPlaylists(): LiveData<Resource<Playlist?>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))

        val response = apiService.fetchAllPlaylist(API_KEY, Constant.PART, Constant.CHANNEL_ID)

        emit(
            if (response.isSuccessful) Resource.success(
                response.body(),
                response.code()
            )
            else Resource.error(
                response.message(),
                response.body(),
                response.code()
            )
        )
    }

    fun fetchYoutubeApiPLaylist(playlistId: String): LiveData<Resource<PlaylistDetail?>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))

            val response = apiService.fetchPlaylist(Constant.PART,Constant.twenty_five, playlistId, API_KEY)

            emit(
                if (response.isSuccessful)
                    Resource.success(
                        response.body(),
                        response.code()
                    )
                else Resource.error(
                    response.message(),
                    response.body(),
                    response.code()
                )
            )
        }
}