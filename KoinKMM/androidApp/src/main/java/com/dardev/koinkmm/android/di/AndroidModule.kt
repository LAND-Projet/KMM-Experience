package com.dardev.koinkmm.android.di

import com.dardev.koinkmm.android.AndroidUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val androidModule = module {
    viewModelOf(::AndroidUserViewModel)
}