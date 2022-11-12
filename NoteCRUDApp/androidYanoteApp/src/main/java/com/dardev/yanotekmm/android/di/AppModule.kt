package com.dardev.yanotekmm.android.di

import android.app.Application
import com.dardev.yanotekmm.data.SqlDelightYanoteDataSource
import com.dardev.yanotekmm.data.local.DatabaseDriverFactory
import com.dardev.yanotekmm.database.YanoteDatabase
import com.dardev.yanotekmm.domain.yanote.YanoteDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideYanoteDataSource(driver: SqlDriver): YanoteDataSource{
        return SqlDelightYanoteDataSource(YanoteDatabase(driver))
    }
}