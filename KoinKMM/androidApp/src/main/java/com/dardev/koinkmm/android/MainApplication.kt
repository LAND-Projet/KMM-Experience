package com.dardev.koinkmm.android

import android.app.Application
import com.dardev.koinkmm.android.di.androidModule
import com.dardev.koinkmm.data.DefaultData
import com.dardev.koinkmm.data.IUserRepository
import com.dardev.koinkmm.di.appModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    private val userRepository : IUserRepository by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(appModule() + androidModule)
        }

        userRepository.addUsers(DefaultData.DEFAULT_USERS)
    }
}