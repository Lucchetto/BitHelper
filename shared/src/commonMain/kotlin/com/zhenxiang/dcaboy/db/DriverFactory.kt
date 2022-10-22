package com.zhenxiang.dcaboy.db

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {

    fun createStorageDbDriver(): SqlDriver
}
