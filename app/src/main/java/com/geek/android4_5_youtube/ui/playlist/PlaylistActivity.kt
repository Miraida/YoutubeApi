package com.geek.android4_5_youtube.ui.playlist

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.geek.android4_5_youtube.`object`.Constant
import com.geek.android4_5_youtube.base.BaseActivity
import com.geek.android4_5_youtube.databinding.ActivityPlaylistBinding
import com.geek.android4_5_youtube.model.Item
import com.geek.android4_5_youtube.ui.detail_playlist.DetailPlaylistActivity
import com.geek.android4_5_youtube.ui.disconnect.NetworkActivity
import com.geek.android4_5_youtube.utils.gone

class PlaylistActivity : BaseActivity<ActivityPlaylistBinding>() {

    private var viewModel: PlaylistViewModel? = null
    private lateinit var adapter: PlaylistAdapter


    override fun showDisconnectState(isAvailable: Boolean) {
        if (!isAvailable) {
            startActivity(Intent(this, NetworkActivity::class.java))
        }
    }

    override fun setupUI() {
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        adapter = PlaylistAdapter(object : PlaylistAdapter.OnItemClickListener {
            override fun onItemClick(id: String) {
                openDetailPlaylist(id)
            }
        })
        ui.rvPlaylists.adapter = adapter
    }

    private fun openDetailPlaylist(id: String) {
        val intent = Intent(this, DetailPlaylistActivity::class.java)
        intent.putExtra(Constant.KEY, id)
        startActivity(intent)
    }

    override fun setupLiveData() {
        viewModel?.fetchPlaylist()?.observe(this, {
            ui.progressbar.gone()
            adapter.updateList(it?.items as ArrayList<Item>)
        })
    }

    override fun bindView(): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }
}