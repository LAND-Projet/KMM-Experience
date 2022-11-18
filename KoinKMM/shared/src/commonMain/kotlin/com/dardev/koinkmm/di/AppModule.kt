package com.dardev.koinkmm.di

import com.dardev.koinkmm.data.IUserRepository
import com.dardev.koinkmm.data.UserRepository
import com.dardev.koinkmm.presenter.KMPUserPresenter
import org.koin.dsl.module

fun appModule() = module {
    single<IUserRepository> { UserRepository() }
    factory { KMPUserPresenter(get()) }
}