package com.geek.android4_5_youtube.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.geek.android4_5_youtube.utils.NetworkMonitorUtil

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private val networkMonitor = NetworkMonitorUtil(this)
    protected lateinit var ui: VB
    protected abstract fun bindView(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = bindView()
        setContentView(ui.root)

        checkNetwork()
        setupUI()
        setupLiveData()
    }

    private fun checkNetwork() {
        networkMonitor.result = { isAvailable ->
            runOnUiThread {
                showDisconnectState(isAvailable)
            }
        }
    }

    abstract fun showDisconnectState(isAvailable: Boolean) // check internet connection

    abstract fun setupUI() // init views

    abstract fun setupLiveData() // init live data

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }
}