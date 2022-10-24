package com.zhenxiang.dcaboy.db

import org.koin.dsl.module

val databaseModule = module {
    single { createStorageDb(DriverFactory()) }
}