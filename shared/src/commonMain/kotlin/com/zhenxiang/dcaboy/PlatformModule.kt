package com.zhenxiang.dcaboy

import org.koin.dsl.module

val platformModule = module {
    single { getPlatform() }
}
