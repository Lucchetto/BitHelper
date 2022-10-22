package com.zhenxiang.dcaboy.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {

    actual fun createStorageDbDriver(): SqlDriver =
        NativeSqliteDriver(StorageDb.Schema, DriverEnv.STORAGE_DB_FILE_NAME)
}