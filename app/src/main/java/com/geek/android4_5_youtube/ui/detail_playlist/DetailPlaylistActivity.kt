package com.geek.android4_5_youtube.ui.detail_playlist

import android.content.Intent
import android.widget.Toast
import com.geek.android4_5_youtube.`object`.Constant
import com.geek.android4_5_youtube.base.BaseActivity
import com.geek.android4_5_youtube.databinding.ActivityDetailPlaylistBinding
import com.geek.android4_5_youtube.ui.disconnect.NetworkActivity

class DetailPlaylistActivity : BaseActivity<ActivityDetailPlaylistBinding>() {

    private fun checkIntent() {
        if (intent != null) {
            Toast.makeText(this, intent.getStringExtra(Constant.KEY), Toast.LENGTH_SHORT).show()
        }
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

    override fun setupUI() {
        checkIntent()
    }

    override fun setupLiveData() {

    }
}