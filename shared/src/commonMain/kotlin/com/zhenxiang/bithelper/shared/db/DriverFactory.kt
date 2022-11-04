package com.zhenxiang.bithelper.shared.db

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory() {

    fun createStorageDbDriver(): SqlDriver
}
