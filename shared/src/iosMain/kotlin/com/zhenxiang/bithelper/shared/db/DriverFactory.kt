package com.zhenxiang.bithelper.shared.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.zhenxiang.bithelper.shared.db.StorageDb
import com.zhenxiang.bithelper.shared.db.DriverEnv

actual class DriverFactory {

    actual fun createStorageDbDriver(): SqlDriver =
        NativeSqliteDriver(StorageDb.Schema, DriverEnv.STORAGE_DB_FILE_NAME)
}