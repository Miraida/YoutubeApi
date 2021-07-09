package com.geek.android4_5_youtube.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(private val layout: Int) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        showDisconnectState()
        setupUI()
        setupLiveData()
    }

    abstract fun showDisconnectState() // check internet connection

    abstract fun setupUI() // init views

    abstract fun setupLiveData() // init live data
}