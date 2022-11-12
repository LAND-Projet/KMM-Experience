package com.dardev.yanotekmm.data.local

import android.content.Context
import com.dardev.yanotekmm.database.YanoteDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(YanoteDatabase.Schema, context, "yanote.db")
    }
}