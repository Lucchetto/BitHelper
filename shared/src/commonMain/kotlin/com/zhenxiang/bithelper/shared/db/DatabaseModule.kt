package com.zhenxiang.bithelper.shared.db

import org.koin.dsl.module

val databaseModule = module {
    single { createStorageDb(DriverFactory()) }
}