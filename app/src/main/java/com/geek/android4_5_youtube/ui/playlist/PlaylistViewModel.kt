package com.geek.android4_5_youtube.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geek.android4_5_youtube.core.network.Resource
import com.geek.android4_5_youtube.model.Playlist
import com.geek.android4_5_youtube.ui.Repository

class PlaylistViewModel(private val repository: Repository) : ViewModel() {

    fun fetchPlaylists(): LiveData<Resource<Playlist?>> {
        return repository.fetchYoutubeApiPlaylists()
    }

    val loading = MutableLiveData<Boolean>()
}