package com.dardev.yanotekmm.di

import com.dardev.yanotekmm.data.SqlDelightYanoteDataSource
import com.dardev.yanotekmm.data.local.DatabaseDriverFactory
import com.dardev.yanotekmm.database.YanoteDatabase
import com.dardev.yanotekmm.domain.yanote.YanoteDataSource

class DatabaseModule {

    private val factory by lazy { DatabaseDriverFactory() }
    val yanoteDataSource: YanoteDataSource by lazy {
        SqlDelightYanoteDataSource(YanoteDatabase(factory.createDriver()))
    }
}