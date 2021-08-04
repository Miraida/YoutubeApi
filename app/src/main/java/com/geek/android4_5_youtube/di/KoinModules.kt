package com.geek.android4_5_youtube.di

import com.geek.android4_5_youtube.data.remote.networkModule

val koinModules = listOf(
    networkModule,
    repoModules,
    viewModules
)