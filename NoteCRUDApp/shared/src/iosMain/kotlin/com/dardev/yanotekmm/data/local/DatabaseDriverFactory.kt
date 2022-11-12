package com.dardev.yanotekmm.data.local

import com.dardev.yanotekmm.database.YanoteDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory{
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(YanoteDatabase.Schema, "yanote.db")
    }
}