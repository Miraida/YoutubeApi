package com.geek.android4_5_youtube.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geek.android4_5_youtube.`object`.Constant
import com.geek.android4_5_youtube.data.remote.ApiService
import com.geek.android4_5_youtube.data.remote.RetrofitClient
import com.geek.android4_5_youtube.model.Playlist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    fun fetchPlaylist(): LiveData<Playlist?> {
        return fetchYoutubeApiPlaylist()
    }

    private var apiService: ApiService? = null

    private fun fetchYoutubeApiPlaylist(): LiveData<Playlist?> {
        apiService = RetrofitClient.create()

        val data = MutableLiveData<Playlist?>()

        apiService!!.fetchAllPlaylist(Constant.API_KEY, Constant.PART, Constant.CHANNEL_ID)
            .enqueue(object :
                Callback<Playlist> {
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    // 404 - not found , 401 -  , 4
                    data.value = null
                }
            })
        return data
    }
}