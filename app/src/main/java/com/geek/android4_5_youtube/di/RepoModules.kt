package com.geek.android4_5_youtube.di

import com.geek.android4_5_youtube.ui.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules : Module = module {
    single { Repository(get()) }
}