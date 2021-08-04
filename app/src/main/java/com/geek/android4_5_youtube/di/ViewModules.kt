package com.geek.android4_5_youtube.di

import com.geek.android4_5_youtube.ui.detail_playlist.DetailPlaylistViewModel
import com.geek.android4_5_youtube.ui.playlist.PlaylistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel {
        DetailPlaylistViewModel(get())
    }
}