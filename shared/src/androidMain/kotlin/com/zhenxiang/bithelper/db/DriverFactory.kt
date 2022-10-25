package com.zhenxiang.bithelper.db

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {

    actual fun createStorageDbDriver(): SqlDriver =
        AndroidSqliteDriver(StorageDb.Schema, context, DriverEnv.STORAGE_DB_FILE_NAME)
}
