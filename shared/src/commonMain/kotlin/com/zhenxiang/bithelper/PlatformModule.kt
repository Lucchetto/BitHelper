package com.zhenxiang.bithelper

import org.koin.dsl.module

val platformModule = module {
    single { getPlatform() }
}
