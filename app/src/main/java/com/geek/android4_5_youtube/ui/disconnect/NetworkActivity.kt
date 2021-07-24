package com.geek.android4_5_youtube.ui.disconnect

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.geek.android4_5_youtube.`object`.Constant
import com.geek.android4_5_youtube.base.BaseActivity
import com.geek.android4_5_youtube.databinding.ActivityNetworkBinding

class NetworkActivity : BaseActivity<ActivityNetworkBinding>() {

    private lateinit var viewModel: NetworkViewModel
    private var isAvailable = false

    override fun bindView(): ActivityNetworkBinding {
        return ActivityNetworkBinding.inflate(layoutInflater)
    }

    override fun showDisconnectState(isAvailable: Boolean) {
        viewModel.setAvailable(isAvailable)
    }

    override fun setupUI() {
        viewModel = ViewModelProvider(this).get(NetworkViewModel::class.java)

        ui.btnTryAgain.setOnClickListener {
            if (isAvailable)
                startTransition()
        }
    }

    private fun startTransition() {
        val className = intent.getStringExtra(Constant.TAG) + ""
        startActivity(
            Intent(
                this,
                className::class.java
            )
        )
    }

    override fun setupLiveData() {
        viewModel.isAvailable.observe(this, {
            isAvailable = it
        })
    }
}