package com.zhenxiang.bithelper.shared.db

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.zhenxiang.bithelper.shared.db.DriverEnv
import com.zhenxiang.bithelper.shared.db.StorageDb
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class DriverFactory : KoinComponent {

    private val context: Context by inject()

    actual fun createStorageDbDriver(): SqlDriver =
        AndroidSqliteDriver(StorageDb.Schema, context, DriverEnv.STORAGE_DB_FILE_NAME)
}
