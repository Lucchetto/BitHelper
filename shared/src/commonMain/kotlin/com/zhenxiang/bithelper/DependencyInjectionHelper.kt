package com.zhenxiang.bithelper

import org.koin.core.context.startKoin

object DependencyInjectionHelper {

    fun initKoin() {
        startKoin {
            modules(platformModule)
        }
    }
}
