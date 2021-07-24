package com.geek.android4_5_youtube.ui.disconnect

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NetworkViewModel : ViewModel() {

    var isAvailable = MutableLiveData<Boolean>()

    fun setAvailable(isAvailable: Boolean) {
        this.isAvailable.value = isAvailable
    }
}