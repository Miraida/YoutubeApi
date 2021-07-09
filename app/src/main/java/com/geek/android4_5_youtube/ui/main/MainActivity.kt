package com.geek.android4_5_youtube.ui.main

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.geek.android4_5_youtube.R
import com.geek.android4_5_youtube.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    private var viewModel: MainViewModel? = null

    override fun showDisconnectState() {

    }

    override fun setupUI() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun setupLiveData() {
        viewModel?.fetchPlaylist()?.observe(this, {
            Toast.makeText(this, it?.kind.toString(), Toast.LENGTH_SHORT).show()
        })
    }

}