package com.zhenxiang.bithelper.db

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory() {

    fun createStorageDbDriver(): SqlDriver
}
