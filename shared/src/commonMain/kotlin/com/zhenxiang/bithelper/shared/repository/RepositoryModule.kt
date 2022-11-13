package com.zhenxiang.bithelper.shared.repository

import org.koin.dsl.module

val repositoryModule = module {
    factory { ApiKeysRepository(get()) }
    factory { parameters -> AccountDataRepository(parameters.get(), get(), get()) }
}
