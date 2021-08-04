package com.geek.android4_5_youtube.ui.detail_playlist


import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.geek.android4_5_youtube.R
import com.geek.android4_5_youtube.core.network.Status
import com.geek.android4_5_youtube.core.ui.BaseActivity
import com.geek.android4_5_youtube.databinding.ActivityDetailPlaylistBinding
import com.geek.android4_5_youtube.model.Item
import com.geek.android4_5_youtube.model.PlaylistDetail
import com.geek.android4_5_youtube.ui.disconnect.NetworkActivity
import com.geek.android4_5_youtube.ui.playlist.PlaylistAdapter
import com.geek.android4_5_youtube.utils.Constant
import com.geek.android4_5_youtube.utils.visibility
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPlaylistActivity : BaseActivity<ActivityDetailPlaylistBinding>() {

    private val viewModel: DetailPlaylistViewModel by viewModel()
    private lateinit var playlistId: String
    private lateinit var playlistTitle: String
    private val adapter by lazy { DetailPlaylistAdapter() }

    private fun checkIntent() {
        if (intent != null) {
            playlistId = intent.getStringExtra(Constant.KEY).toString()
            playlistTitle = intent.getStringExtra(Constant.TITLE_KEY).toString()
        }
    }

    override fun setupUI() {
        checkIntent()
        ui.rvDetailPlaylist.adapter = adapter

        adapter.setupListener(object : PlaylistAdapter.OnItemClickListener {
            override fun onItemClick(id: String, title: String) {
                Toast.makeText(this@DetailPlaylistActivity, "$id $title", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun setupLiveData() {
        viewModel.loading.observe(this, {
            ui.progressBar.visibility(it)
        })

        viewModel.fetchPlaylist(playlistId).observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data != null) {
                        viewModel.loading.postValue(false)
                        adapter.updateList(it.data.items as ArrayList<Item>)
                        setupData(it.data)
                    }
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                    Log.d("TAG", "DetailPlaylistActivity: " + it.msg + " code: " + it.code)
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
            }
        })
    }

    private fun setupData(data: PlaylistDetail) {
        ui.tvSubTitle.text = data.kind
        ui.tvNumberOfVideos.text =
            data.items?.size.toString().plus(" ").plus(getString(R.string.video_series))
        ui.tvTitle.text = playlistTitle
    }

    override fun bindView(): ActivityDetailPlaylistBinding {
        return ActivityDetailPlaylistBinding.inflate(layoutInflater)
    }

    override fun showDisconnectState(isAvailable: Boolean) {
        if (!isAvailable) {
            startActivity(getIntentWithTag())
        }
    }

    private fun getIntentWithTag(): Intent {
        val intent = Intent(this, NetworkActivity::class.java)
        intent.putExtra(Constant.TAG, localClassName)
        return intent
    }

}