package com.geek.android4_5_youtube.ui.disconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geek.android4_5_youtube.databinding.ActivityNetworkBinding
import com.geek.android4_5_youtube.ui.playlist.PlaylistActivity

class NetworkActivity : AppCompatActivity() {

    private lateinit var ui: ActivityNetworkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = ActivityNetworkBinding.inflate(layoutInflater)
        setContentView(ui.root)

        setupListener()
    }

    private fun setupListener() {
        ui.btnTryAgain.setOnClickListener {
            startActivity(Intent(this, PlaylistActivity::class.java))
        }
    }
}