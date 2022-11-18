package com.dardev.koinkmm

import com.dardev.koinkmm.data.DefaultData
import com.dardev.koinkmm.data.IUserRepository
import com.dardev.koinkmm.di.appModule
import org.koin.core.context.startKoin

fun initKoin() {
    val koinApp = startKoin {
        modules(appModule())
    }.koin

    koinApp.get<IUserRepository>().addUsers(DefaultData.DEFAULT_USERS)
}