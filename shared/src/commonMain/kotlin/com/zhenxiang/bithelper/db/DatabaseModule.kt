package com.zhenxiang.bithelper.db

import org.koin.dsl.module

val databaseModule = module {
    single { createStorageDb(DriverFactory()) }
}