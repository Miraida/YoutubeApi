package com.geek.android4_5_youtube.ui.playlist

import android.content.Intent
import android.util.Log

import com.geek.android4_5_youtube.core.network.Status
import com.geek.android4_5_youtube.core.ui.BaseActivity
import com.geek.android4_5_youtube.databinding.ActivityPlaylistBinding
import com.geek.android4_5_youtube.model.Item
import com.geek.android4_5_youtube.ui.detail_playlist.DetailPlaylistActivity
import com.geek.android4_5_youtube.ui.disconnect.NetworkActivity
import com.geek.android4_5_youtube.utils.Constant
import com.geek.android4_5_youtube.utils.visibility
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistActivity : BaseActivity<ActivityPlaylistBinding>() {

    private val viewModel: PlaylistViewModel by viewModel()
    private lateinit var adapter: PlaylistAdapter


    override fun showDisconnectState(isAvailable: Boolean) {
        if (!isAvailable) {
            startActivity(getIntentWithTag())
        }
    }

    override fun setupUI() {
        adapter = PlaylistAdapter(this::openDetailPlaylist)
        ui.rvPlaylists.adapter = adapter
    }

    private fun openDetailPlaylist(id: String,title:String) {
        val intent = Intent(this, DetailPlaylistActivity::class.java)
        intent.putExtra(Constant.KEY, id)
        intent.putExtra(Constant.TITLE_KEY,title)
        startActivity(intent)
    }

    override fun setupLiveData() {
        viewModel.loading.observe(this, {
            ui.progressbar.visibility(it)
        })

        viewModel.fetchPlaylists().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data != null) {
                        viewModel.loading.postValue(false)
                        adapter.updateList(it.data.items as ArrayList<Item>)
                    }
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                    Log.d("TAG", "setupLiveData: " + it.msg)
                }
            }
        })
    }

    override fun bindView(): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    private fun getIntentWithTag(): Intent {
        val intent = Intent(this, NetworkActivity::class.java)
        intent.putExtra(Constant.TAG, localClassName)
        return intent
    }
}
