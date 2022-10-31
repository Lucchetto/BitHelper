package com.zhenxiang.bithelper.repository

import org.koin.dsl.module

val repositoryModule = module {
    factory { ApiKeysRepository(get()) }
}
