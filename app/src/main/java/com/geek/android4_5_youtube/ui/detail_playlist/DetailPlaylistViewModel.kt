package com.geek.android4_5_youtube.ui.detail_playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geek.android4_5_youtube.core.network.Resource
import com.geek.android4_5_youtube.model.PlaylistDetail
import com.geek.android4_5_youtube.ui.Repository

class DetailPlaylistViewModel(private val repository: Repository) : ViewModel() {

    fun fetchPlaylist(playlistId: String): LiveData<Resource<PlaylistDetail?>> {
        return repository.fetchYoutubeApiPLaylist(playlistId)
    }

    val loading = MutableLiveData<Boolean>()
}