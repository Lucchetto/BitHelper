package com.zhenxiang.dcaboy.db

fun createStorageDb(driverFactory: DriverFactory): StorageDb =
    StorageDb(driverFactory.createStorageDbDriver())
